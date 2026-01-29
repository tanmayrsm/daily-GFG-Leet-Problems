# SOLID Principles in Java

SOLID is an acronym for five design principles that make software designs more understandable, flexible, and maintainable.

## Table of Contents
1. [Single Responsibility Principle (SRP)](#single-responsibility-principle-srp)
2. [Open/Closed Principle (OCP)](#openclosed-principle-ocp)
3. [Liskov Substitution Principle (LSP)](#liskov-substitution-principle-lsp)
4. [Interface Segregation Principle (ISP)](#interface-segregation-principle-isp)
5. [Dependency Inversion Principle (DIP)](#dependency-inversion-principle-dip)

---

## Single Responsibility Principle (SRP)

**Definition**: A class should have only one reason to change, meaning it should have only one job or responsibility.

### Bad Example (Violates SRP)

```java
// This class has multiple responsibilities: user data management,
// database operations, and email notifications
public class User {
    private String name;
    private String email;

    public void saveToDatabase() {
        // Database logic here
        System.out.println("Saving user to database...");
    }

    public void sendEmail(String message) {
        // Email sending logic here
        System.out.println("Sending email to " + email);
    }

    public void generateReport() {
        // Report generation logic here
        System.out.println("Generating user report...");
    }
}
```

### Good Example (Follows SRP)

```java
// User class only manages user data
public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
}

// Separate class for database operations
public class UserRepository {
    public void save(User user) {
        System.out.println("Saving user to database: " + user.getName());
    }
}

// Separate class for email operations
public class EmailService {
    public void sendEmail(User user, String message) {
        System.out.println("Sending email to " + user.getEmail() + ": " + message);
    }
}

// Separate class for report generation
public class UserReportGenerator {
    public void generate(User user) {
        System.out.println("Generating report for user: " + user.getName());
    }
}
```

---

## Open/Closed Principle (OCP)

**Definition**: Software entities should be open for extension but closed for modification. You should be able to add new functionality without changing existing code.

### Bad Example (Violates OCP)

```java
public class PaymentProcessor {
    public void processPayment(String paymentType, double amount) {
        if (paymentType.equals("CREDIT_CARD")) {
            System.out.println("Processing credit card payment: $" + amount);
        } else if (paymentType.equals("PAYPAL")) {
            System.out.println("Processing PayPal payment: $" + amount);
        } else if (paymentType.equals("BITCOIN")) {
            // Need to modify this class to add new payment method
            System.out.println("Processing Bitcoin payment: $" + amount);
        }
    }
}
```

### Good Example (Follows OCP)

```java
// Define an interface for payment methods
public interface PaymentMethod {
    void processPayment(double amount);
}

// Implement different payment methods
public class CreditCardPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
    }
}

public class PayPalPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
    }
}

public class BitcoinPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Bitcoin payment: $" + amount);
    }
}

// PaymentProcessor is now closed for modification but open for extension
public class PaymentProcessor {
    public void processPayment(PaymentMethod paymentMethod, double amount) {
        paymentMethod.processPayment(amount);
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        processor.processPayment(new CreditCardPayment(), 100.0);
        processor.processPayment(new PayPalPayment(), 200.0);
        processor.processPayment(new BitcoinPayment(), 300.0);
    }
}
```

---

## Liskov Substitution Principle (LSP)

**Definition**: Objects of a superclass should be replaceable with objects of a subclass without breaking the application. Subtypes must be substitutable for their base types.

### Bad Example (Violates LSP)

```java
public class Bird {
    public void fly() {
        System.out.println("Flying...");
    }
}

// Penguin is a bird but cannot fly - violates LSP
public class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins cannot fly!");
    }
}

// This will break if we pass a Penguin
public class BirdWatcher {
    public void watchBirdFly(Bird bird) {
        bird.fly(); // Will throw exception if bird is a Penguin
    }
}
```

### Good Example (Follows LSP)

```java
// Base class for all birds
public abstract class Bird {
    public abstract void move();
}

// Separate interface for flying birds
public interface Flyable {
    void fly();
}

// Flying birds implement both Bird and Flyable
public class Sparrow extends Bird implements Flyable {
    @Override
    public void move() {
        fly();
    }

    @Override
    public void fly() {
        System.out.println("Sparrow is flying...");
    }
}

// Non-flying birds only extend Bird
public class Penguin extends Bird {
    @Override
    public void move() {
        swim();
    }

    public void swim() {
        System.out.println("Penguin is swimming...");
    }
}

// BirdWatcher now works correctly with all birds
public class BirdWatcher {
    public void watchBirdMove(Bird bird) {
        bird.move(); // Works for all bird types
    }

    public void watchBirdFly(Flyable flyingBird) {
        flyingBird.fly(); // Only accepts birds that can fly
    }
}
```

---

## Interface Segregation Principle (ISP)

**Definition**: Clients should not be forced to depend on interfaces they don't use. Many specific interfaces are better than one general-purpose interface.

### Bad Example (Violates ISP)

```java
// Fat interface that forces classes to implement methods they don't need
public interface Worker {
    void work();
    void eat();
    void sleep();
    void getPaid();
}

// Robot worker doesn't eat or sleep but is forced to implement these methods
public class RobotWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Robot working...");
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robots don't eat!");
    }

    @Override
    public void sleep() {
        throw new UnsupportedOperationException("Robots don't sleep!");
    }

    @Override
    public void getPaid() {
        throw new UnsupportedOperationException("Robots don't get paid!");
    }
}
```

### Good Example (Follows ISP)

```java
// Segregated interfaces
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public interface Sleepable {
    void sleep();
}

public interface Payable {
    void getPaid();
}

// Human worker implements all interfaces
public class HumanWorker implements Workable, Eatable, Sleepable, Payable {
    @Override
    public void work() {
        System.out.println("Human working...");
    }

    @Override
    public void eat() {
        System.out.println("Human eating...");
    }

    @Override
    public void sleep() {
        System.out.println("Human sleeping...");
    }

    @Override
    public void getPaid() {
        System.out.println("Human getting paid...");
    }
}

// Robot worker only implements what it needs
public class RobotWorker implements Workable {
    @Override
    public void work() {
        System.out.println("Robot working...");
    }
}

// Manager can work with any workable entity
public class WorkManager {
    public void manage(Workable worker) {
        worker.work();
    }
}
```

---

## Dependency Inversion Principle (DIP)

**Definition**: High-level modules should not depend on low-level modules. Both should depend on abstractions. Abstractions should not depend on details; details should depend on abstractions.

### Bad Example (Violates DIP)

```java
// Low-level module
public class MySQLDatabase {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

// High-level module directly depends on low-level module
public class UserService {
    private MySQLDatabase database; // Tight coupling to concrete class

    public UserService() {
        this.database = new MySQLDatabase();
    }

    public void saveUser(String userData) {
        database.save(userData);
    }
}

// Switching to MongoDB requires modifying UserService
```

### Good Example (Follows DIP)

```java
// Abstraction
public interface Database {
    void save(String data);
}

// Low-level modules implement the abstraction
public class MySQLDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

public class MongoDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to MongoDB: " + data);
    }
}

public class PostgreSQLDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to PostgreSQL: " + data);
    }
}

// High-level module depends on abstraction
public class UserService {
    private Database database; // Depends on abstraction, not concrete class

    // Dependency injection via constructor
    public UserService(Database database) {
        this.database = database;
    }

    public void saveUser(String userData) {
        database.save(userData);
    }
}

// Usage - can easily switch databases without modifying UserService
public class Main {
    public static void main(String[] args) {
        // Using MySQL
        Database mysqlDb = new MySQLDatabase();
        UserService userService1 = new UserService(mysqlDb);
        userService1.saveUser("User1");

        // Switching to MongoDB - no changes to UserService needed
        Database mongoDb = new MongoDatabase();
        UserService userService2 = new UserService(mongoDb);
        userService2.saveUser("User2");

        // Switching to PostgreSQL
        Database postgresDb = new PostgreSQLDatabase();
        UserService userService3 = new UserService(postgresDb);
        userService3.saveUser("User3");
    }
}
```

---

## Real-World Example: E-Commerce Order Processing System

Here's a comprehensive example that demonstrates all SOLID principles together:

```java
// DIP & ISP: Payment abstraction
public interface PaymentProcessor {
    boolean processPayment(double amount);
}

// DIP & ISP: Notification abstraction
public interface NotificationService {
    void sendNotification(String message);
}

// DIP & ISP: Order storage abstraction
public interface OrderRepository {
    void save(Order order);
}

// SRP: Order entity only contains order data
public class Order {
    private String orderId;
    private double totalAmount;
    private OrderStatus status;

    public Order(String orderId, double totalAmount) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.PENDING;
    }

    public String getOrderId() { return orderId; }
    public double getTotalAmount() { return totalAmount; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}

public enum OrderStatus {
    PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
}

// OCP: Different payment implementations
public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
        return true;
    }
}

public class PayPalProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
        return true;
    }
}

// OCP: Different notification implementations
public class EmailNotification implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email: " + message);
    }
}

public class SMSNotification implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// SRP & DIP: Order repository implementation
public class DatabaseOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Saving order to database: " + order.getOrderId());
    }
}

// SRP: Order service only handles order processing logic
// DIP: Depends on abstractions, not concrete implementations
public class OrderService {
    private final PaymentProcessor paymentProcessor;
    private final NotificationService notificationService;
    private final OrderRepository orderRepository;

    // Dependency injection via constructor
    public OrderService(PaymentProcessor paymentProcessor,
                       NotificationService notificationService,
                       OrderRepository orderRepository) {
        this.paymentProcessor = paymentProcessor;
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
    }

    public void processOrder(Order order) {
        // Process payment
        boolean paymentSuccess = paymentProcessor.processPayment(order.getTotalAmount());

        if (paymentSuccess) {
            order.setStatus(OrderStatus.CONFIRMED);
            orderRepository.save(order);
            notificationService.sendNotification(
                "Order " + order.getOrderId() + " confirmed!"
            );
        } else {
            order.setStatus(OrderStatus.CANCELLED);
            notificationService.sendNotification(
                "Order " + order.getOrderId() + " payment failed!"
            );
        }
    }
}

// Usage example
public class ECommerceApp {
    public static void main(String[] args) {
        // Create dependencies
        PaymentProcessor paymentProcessor = new CreditCardProcessor();
        NotificationService notificationService = new EmailNotification();
        OrderRepository orderRepository = new DatabaseOrderRepository();

        // Inject dependencies
        OrderService orderService = new OrderService(
            paymentProcessor,
            notificationService,
            orderRepository
        );

        // Process order
        Order order = new Order("ORD-001", 299.99);
        orderService.processOrder(order);

        // Easy to switch implementations without changing OrderService
        OrderService smsOrderService = new OrderService(
            new PayPalProcessor(),
            new SMSNotification(),
            orderRepository
        );

        Order order2 = new Order("ORD-002", 149.99);
        smsOrderService.processOrder(order2);
    }
}
```

---

## Benefits of Following SOLID Principles

1. **Maintainability**: Code is easier to understand and modify
2. **Testability**: Classes with single responsibilities are easier to test
3. **Flexibility**: Easy to extend functionality without breaking existing code
4. **Reusability**: Well-designed components can be reused across projects
5. **Reduced Coupling**: Components are loosely coupled and more independent
6. **Better Collaboration**: Clear responsibilities make teamwork easier

## Summary

- **SRP**: One class, one responsibility
- **OCP**: Extend behavior without modifying existing code
- **LSP**: Subtypes must be substitutable for their base types
- **ISP**: Many specific interfaces are better than one general interface
- **DIP**: Depend on abstractions, not concretions

Following these principles leads to cleaner, more maintainable, and more scalable code.