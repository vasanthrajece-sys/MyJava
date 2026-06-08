# Vasanth JPA Repository Practice

Spring Boot backend project for practicing Spring Data JPA repository topics.

It uses H2 in-memory database, so no database install is needed.

## Topics Included

- `JpaRepository`
- basic CRUD
- derived finder methods
- `existsBy`, `countBy`, `deleteBy`
- sorting
- pagination
- JPQL `@Query`
- native SQL `@Query`
- update query with `@Modifying`
- interface projection
- DTO projection
- entity relationship: `Employee` many-to-one `Department`
- transaction usage
- auditing: `createdAt`, `updatedAt`
- JPA Specification dynamic filtering

## Run

Open/import this folder as a Maven project:

```text
jpa-repository-demo
```

Run:

```text
JpaRepositoryDemoApplication
```

## H2 Console

```text
http://localhost:8095/h2-console
```

Use:

```text
JDBC URL: jdbc:h2:mem:vasanth_jpa
User: sa
Password:
```

## Useful URLs

```text
GET http://localhost:8095/jpa/employees
GET http://localhost:8095/jpa/employees/department/Backend
GET http://localhost:8095/jpa/employees/salary-greater-than?salary=70000
GET http://localhost:8095/jpa/employees/page?page=0&size=2
GET http://localhost:8095/jpa/employees/sorted
GET http://localhost:8095/jpa/employees/projection
GET http://localhost:8095/jpa/employees/dto
GET http://localhost:8095/jpa/employees/search?department=Backend&minSalary=70000
PUT http://localhost:8095/jpa/employees/1/salary?salary=98000
DELETE http://localhost:8095/jpa/employees/name/Karthik
```
