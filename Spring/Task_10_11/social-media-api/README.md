# Social Media API

Spring Boot REST API demonstrating:

- User and Post entities
- One-to-Many / Many-to-One relationship
- DTOs
- MapStruct mappers
- Bean Validation
- Service / ServiceImpl layers
- Global exception handling
- English and Arabic error messages
- Oracle Database with Spring Data JPA

## User endpoints

- `POST /users`
- `GET /users/{id}`
- `GET /users`
- `PUT /users/{id}`
- `DELETE /users/{id}`
- `GET /users/{id}/posts`
- `GET /users/usersWithPost`
- `GET /users/userWithPost/{id}`

## Post endpoints

- `POST /posts`
- `GET /posts/{id}`
- `GET /posts`
- `PUT /posts/{id}`
- `DELETE /posts/{id}`
- `GET /posts/postsWithUsers`
- `GET /posts/postWithUsers/{id}`

## Database configuration

Default values are compatible with the original project:

- URL: `jdbc:oracle:thin:@//localhost:1521/orclpdb`
- Username: `hr`
- Password: `hr`

They can be overridden with environment variables:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
