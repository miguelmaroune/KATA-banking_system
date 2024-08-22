# KATA-banking_system

a simplified banking system that handles customer accounts, transactions, and supports multiple currency accounts. It should support secure RESTful endpoints for managing accounts, processing transactions, and viewing statements.

Requirements:
Entities:

Customer: Represents a customer with basic information like name, email, and address.
Account: Each customer can have multiple accounts in different currencies (USD, EUR, etc.).
Transaction: Represents a transfer between accounts. It should support multiple transaction types such as deposit, withdrawal, and transfer.
Functionality:

Customer Management:
Create a new customer.
Retrieve customer details.
Account Management:
Create an account for a customer (support multiple currencies).
Retrieve account balance.
Transactions:
Process deposits, withdrawals, and transfers between accounts.
Ensure transactions are atomic and consistent (use Spring’s transaction management).
Support currency conversion during transactions (e.g., transferring USD to an EUR account).
Transaction History:
Retrieve a customer’s transaction history with filtering options (by date, account, transaction type).
Security:

Implement JWT-based authentication for all endpoints.
Use role-based access control (e.g., admin and customer roles).
Allow customers to only view and manage their own accounts and transactions.
Performance Optimization:

Implement caching (e.g., using Spring Cache) for frequently accessed data like exchange rates.
Use pagination when retrieving large transaction histories.
Data Validation and Error Handling:

Proper validation for all inputs (e.g., valid email, positive account balance).
Handle and return meaningful error messages for edge cases (e.g., insufficient funds, invalid currency).
Logging and Monitoring:

Integrate Spring Boot Actuator for health checks and monitoring.
Log all transactions and significant actions with audit trails.
Technical Constraints:
Use Spring Boot 3.x.
Use Spring Data JPA for persistence.
Use an in-memory H2 database for simplicity, but design it to be easily swapped out for a real database like MySQL or PostgreSQL.
Use JUnit 5 and Mockito for unit testing.
Document the API using Springdoc OpenAPI (Swagger).
Optional Stretch Goals:
Implement a scheduler to calculate and apply monthly interest to savings accounts.
Support multi-tenancy, allowing the system to serve multiple banks with isolated data.
Introduce event-driven communication (e.g., using Kafka) for handling transactions asynchronously.
