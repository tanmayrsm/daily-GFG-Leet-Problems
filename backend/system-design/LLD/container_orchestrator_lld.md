# Container Orchestrator (Java LLD)

This is the concrete LLD for a simple container scheduler:
- Policy driven scheduling
- Pluggable machine selection strategy
- Facade entrypoint
- Factory for machine creation
- Decorator for metrics/logging
- Concurrency-safe resource updates

## 1. Domain

```java
Resource { cpuUnits, memoryMb }
ContainerSpec { id, image, requested }
Container { id, spec }
Machine { id, type, total, used, runningContainers }
```

`Machine.canHost/start/stop` are synchronized to keep resource accounting correct.

## 2. Policy

```java
enum SchedulingPolicyType { LEAST_LOADED, ROUND_ROBIN }

class SchedulingPolicy {
    SchedulingPolicyType type;
}

interface SchedulingPolicyStore {
    SchedulingPolicy getPolicy();
}
```

## 3. Strategy (machine selection)

```java
interface MachineSelectionStrategy {
    Machine choose(List<Machine> machines, ContainerSpec spec);
}
```

Concrete strategies:
- `LeastLoadedStrategy`
- `RoundRobinStrategy`

Registry:

```java
class MachineSelectionStrategyRegistry {
    Map<SchedulingPolicyType, MachineSelectionStrategy> strategies;
    MachineSelectionStrategy getStrategy(SchedulingPolicyType type) { ... }
}
```

## 4. Facade

```java
interface ContainerOrchestrator {
    Machine schedule(ContainerSpec spec);
    void stopContainer(String containerId);
    void addMachine(Machine machine);
    List<Machine> listMachines();
}
```

`InMemoryContainerOrchestrator.schedule(...)` flow:
1. Get policy
2. Resolve strategy
3. Snapshot machine list
4. Choose machine
5. Start container
6. Persist container->machine mapping

## 5. Factory

```java
interface MachineFactory {
    Machine create(MachineType type, String id, Resource total);
}
```

`DefaultMachineFactory` centralizes machine construction.

## 6. Decorator

`MetricsOrchestrator implements ContainerOrchestrator` wraps another orchestrator and records latency.

## 7. Actual Java Example

See:
- [ContainerOrchestratorExample.java](/Users/tamishra/Documents/dlyPrb/backend/system-design/LLD/ContainerOrchestratorExample.java)

