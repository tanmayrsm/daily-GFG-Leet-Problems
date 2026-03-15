import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ContainerOrchestratorExample {

    static class Resource {
        int cpuUnits;
        int memoryMb;

        Resource() {}

        Resource(int cpuUnits, int memoryMb) {
            this.cpuUnits = cpuUnits;
            this.memoryMb = memoryMb;
        }
    }

    static class ContainerSpec {
        final String id;
        final String image;
        final Resource requested;

        ContainerSpec(String id, String image, Resource requested) {
            this.id = id;
            this.image = image;
            this.requested = requested;
        }
    }

    static class Container {
        String id;
        ContainerSpec spec;
    }

    enum MachineType {
        AMAZON_EC2,
        AZURE_VM
    }

    abstract static class Machine {
        String id;
        MachineType type;
        Resource total;
        Resource used;
        final List<Container> runningContainers = new ArrayList<>();

        synchronized boolean canHost(ContainerSpec spec) {
            int availableCpu = total.cpuUnits - used.cpuUnits;
            int availableMem = total.memoryMb - used.memoryMb;
            return availableCpu >= spec.requested.cpuUnits
                && availableMem >= spec.requested.memoryMb;
        }

        synchronized Container startContainer(ContainerSpec spec) {
            if (!canHost(spec)) {
                throw new IllegalStateException("Not enough resources on machine " + id);
            }
            used.cpuUnits += spec.requested.cpuUnits;
            used.memoryMb += spec.requested.memoryMb;

            Container c = new Container();
            c.id = spec.id;
            c.spec = spec;
            runningContainers.add(c);
            startContainerImpl(c);
            return c;
        }

        synchronized void stopContainer(String containerId) {
            Container found = null;
            for (Container c : runningContainers) {
                if (c.id.equals(containerId)) {
                    found = c;
                    break;
                }
            }
            if (found == null) {
                return;
            }

            used.cpuUnits -= found.spec.requested.cpuUnits;
            used.memoryMb -= found.spec.requested.memoryMb;
            runningContainers.remove(found);
            stopContainerImpl(found);
        }

        protected abstract void startContainerImpl(Container c);

        protected abstract void stopContainerImpl(Container c);
    }

    static class AmazonMachine extends Machine {
        @Override
        protected void startContainerImpl(Container c) {
            System.out.println("[AWS] start " + c.id + " on " + id);
        }

        @Override
        protected void stopContainerImpl(Container c) {
            System.out.println("[AWS] stop " + c.id + " on " + id);
        }
    }

    static class AzureMachine extends Machine {
        @Override
        protected void startContainerImpl(Container c) {
            System.out.println("[AZURE] start " + c.id + " on " + id);
        }

        @Override
        protected void stopContainerImpl(Container c) {
            System.out.println("[AZURE] stop " + c.id + " on " + id);
        }
    }

    enum SchedulingPolicyType {
        LEAST_LOADED,
        ROUND_ROBIN
    }

    static class SchedulingPolicy {
        final SchedulingPolicyType type;

        SchedulingPolicy(SchedulingPolicyType type) {
            this.type = type;
        }
    }

    interface SchedulingPolicyStore {
        SchedulingPolicy getPolicy();
    }

    static class InMemorySchedulingPolicyStore implements SchedulingPolicyStore {
        private volatile SchedulingPolicy policy;

        InMemorySchedulingPolicyStore(SchedulingPolicy policy) {
            this.policy = policy;
        }

        void setPolicy(SchedulingPolicy policy) {
            this.policy = policy;
        }

        @Override
        public SchedulingPolicy getPolicy() {
            return policy;
        }
    }

    interface MachineSelectionStrategy {
        Machine choose(List<Machine> machines, ContainerSpec spec);
    }

    static class LeastLoadedStrategy implements MachineSelectionStrategy {
        @Override
        public Machine choose(List<Machine> machines, ContainerSpec spec) {
            Machine best = null;
            int bestScore = Integer.MIN_VALUE;

            for (Machine m : machines) {
                synchronized (m) {
                    if (!m.canHost(spec)) {
                        continue;
                    }
                    int freeCpu = m.total.cpuUnits - m.used.cpuUnits;
                    int freeMem = m.total.memoryMb - m.used.memoryMb;
                    int score = freeCpu + freeMem;
                    if (score > bestScore) {
                        bestScore = score;
                        best = m;
                    }
                }
            }

            if (best == null) {
                throw new IllegalStateException("No eligible machine");
            }
            return best;
        }
    }

    static class RoundRobinStrategy implements MachineSelectionStrategy {
        private final AtomicInteger index = new AtomicInteger(0);

        @Override
        public Machine choose(List<Machine> machines, ContainerSpec spec) {
            int n = machines.size();
            if (n == 0) {
                throw new IllegalStateException("No machines available");
            }
            for (int i = 0; i < n; i++) {
                int idx = Math.floorMod(index.getAndIncrement(), n);
                Machine m = machines.get(idx);
                if (m.canHost(spec)) {
                    return m;
                }
            }
            throw new IllegalStateException("No eligible machine");
        }
    }

    static class MachineSelectionStrategyRegistry {
        private final Map<SchedulingPolicyType, MachineSelectionStrategy> strategies;

        MachineSelectionStrategyRegistry(Map<SchedulingPolicyType, MachineSelectionStrategy> strategies) {
            this.strategies = strategies;
        }

        MachineSelectionStrategy getStrategy(SchedulingPolicyType type) {
            MachineSelectionStrategy strategy = strategies.get(type);
            if (strategy == null) {
                throw new IllegalArgumentException("No strategy for type: " + type);
            }
            return strategy;
        }
    }

    interface ContainerOrchestrator {
        Machine schedule(ContainerSpec spec);

        void stopContainer(String containerId);

        void addMachine(Machine machine);

        List<Machine> listMachines();
    }

    static class InMemoryContainerOrchestrator implements ContainerOrchestrator {
        private final List<Machine> machines = Collections.synchronizedList(new ArrayList<>());
        private final SchedulingPolicyStore policyStore;
        private final MachineSelectionStrategyRegistry strategyRegistry;
        private final ConcurrentHashMap<String, Machine> containerToMachine = new ConcurrentHashMap<>();

        InMemoryContainerOrchestrator(
            SchedulingPolicyStore policyStore,
            MachineSelectionStrategyRegistry strategyRegistry
        ) {
            this.policyStore = policyStore;
            this.strategyRegistry = strategyRegistry;
        }

        @Override
        public void addMachine(Machine machine) {
            machines.add(machine);
        }

        @Override
        public List<Machine> listMachines() {
            synchronized (machines) {
                return new ArrayList<>(machines);
            }
        }

        @Override
        public Machine schedule(ContainerSpec spec) {
            SchedulingPolicy policy = policyStore.getPolicy();
            MachineSelectionStrategy strategy = strategyRegistry.getStrategy(policy.type);

            List<Machine> snapshot;
            synchronized (machines) {
                snapshot = new ArrayList<>(machines);
            }

            Machine chosen = strategy.choose(snapshot, spec);
            chosen.startContainer(spec);
            containerToMachine.put(spec.id, chosen);
            return chosen;
        }

        @Override
        public void stopContainer(String containerId) {
            Machine machine = containerToMachine.get(containerId);
            if (machine == null) {
                return;
            }
            machine.stopContainer(containerId);
            containerToMachine.remove(containerId);
        }
    }

    interface MachineFactory {
        Machine create(MachineType type, String id, Resource total);
    }

    static class DefaultMachineFactory implements MachineFactory {
        @Override
        public Machine create(MachineType type, String id, Resource total) {
            Machine m;
            switch (type) {
                case AMAZON_EC2:
                    m = new AmazonMachine();
                    break;
                case AZURE_VM:
                    m = new AzureMachine();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported machine type: " + type);
            }
            m.id = id;
            m.type = type;
            m.total = total;
            m.used = new Resource(0, 0);
            return m;
        }
    }

    static class MetricsOrchestrator implements ContainerOrchestrator {
        private final ContainerOrchestrator inner;

        MetricsOrchestrator(ContainerOrchestrator inner) {
            this.inner = inner;
        }

        @Override
        public Machine schedule(ContainerSpec spec) {
            long start = System.nanoTime();
            Machine m = inner.schedule(spec);
            long end = System.nanoTime();
            System.out.println("[METRIC] scheduleLatencyNanos=" + (end - start));
            return m;
        }

        @Override
        public void stopContainer(String containerId) {
            inner.stopContainer(containerId);
        }

        @Override
        public void addMachine(Machine machine) {
            inner.addMachine(machine);
        }

        @Override
        public List<Machine> listMachines() {
            return inner.listMachines();
        }
    }

    public static void main(String[] args) {
        InMemorySchedulingPolicyStore policyStore =
            new InMemorySchedulingPolicyStore(new SchedulingPolicy(SchedulingPolicyType.LEAST_LOADED));

        Map<SchedulingPolicyType, MachineSelectionStrategy> strategies = new HashMap<>();
        strategies.put(SchedulingPolicyType.LEAST_LOADED, new LeastLoadedStrategy());
        strategies.put(SchedulingPolicyType.ROUND_ROBIN, new RoundRobinStrategy());

        MachineSelectionStrategyRegistry registry = new MachineSelectionStrategyRegistry(strategies);
        InMemoryContainerOrchestrator core = new InMemoryContainerOrchestrator(policyStore, registry);
        ContainerOrchestrator orchestrator = new MetricsOrchestrator(core);

        MachineFactory factory = new DefaultMachineFactory();
        orchestrator.addMachine(factory.create(MachineType.AMAZON_EC2, "m-1", new Resource(8, 16000)));
        orchestrator.addMachine(factory.create(MachineType.AZURE_VM, "m-2", new Resource(4, 8000)));

        ContainerSpec c1 = new ContainerSpec("c-1", "nginx:latest", new Resource(2, 1000));
        ContainerSpec c2 = new ContainerSpec("c-2", "redis:7", new Resource(2, 1200));
        ContainerSpec c3 = new ContainerSpec("c-3", "java-app:v1", new Resource(4, 4000));

        Machine h1 = orchestrator.schedule(c1);
        Machine h2 = orchestrator.schedule(c2);
        Machine h3 = orchestrator.schedule(c3);

        System.out.println("Placed c-1 on " + h1.id);
        System.out.println("Placed c-2 on " + h2.id);
        System.out.println("Placed c-3 on " + h3.id);

        orchestrator.stopContainer("c-2");
    }
}

