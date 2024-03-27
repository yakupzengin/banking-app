# Banking API

This project implements a simple banking API. The API supports basic banking operations such as account creation, getting account information, depositing money, withdrawing money, and deleting an account.

## Technologies Used

- Spring Boot
- Spring Data JPA
- Lombok
- H2 Database (or any desired database)

## Installation

1. Clone the repository: `git clone https://github.com/username/banking-api.git`
2. Navigate to the project directory: `cd banking-api`
3. Install project dependencies: `mvn install` or `./mvnw install` (Maven Wrapper can be used if Maven is not installed)
4. Run the application: `mvn spring-boot:run` or `./mvnw spring-boot:run`

## API Usage

- `GET /api/accounts`: Retrieves all account information.
- `GET /api/accounts/{id}`: Retrieves information for a specific account.
- `POST /api/accounts`: Creates a new account.
- `PUT /api/accounts/{id}/deposit`: Deposits money into a specific account.
- `PUT /api/accounts/{id}/withdraw`: Withdraws money from a specific account.
- `DELETE /api/accounts/{id}`: Deletes a specific account.

## Contributing

1. Fork the repository (https://github.com/username/banking-api/fork)
2. Create a new feature branch: `git checkout -b new-feature`
3. Make your changes and commit them: `git commit -am 'Add new feature'`
4. Push your branch: `git push origin new-feature`
5. Create a Pull Request
