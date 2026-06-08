# Vasanth Okta Microservices Practice

Two pure backend Spring Boot services:

- `token-client-service` runs on port `8081`
- `resource-service` runs on port `8082`

Flow:

```text
Browser/Postman
    -> token-client-service
    -> Okta token endpoint
    -> token-client-service sends Bearer token
    -> resource-service validates JWT using Spring Security
```

## Okta Setup Needed

Create an Okta OIDC app for machine-to-machine/client credentials.

You need:

```text
issuer-uri
client-id
client-secret
```

Example issuer format:

```text
https://dev-12345678.okta.com/oauth2/default
```

## Configure

Update:

```text
token-client-service/src/main/resources/application.properties
resource-service/src/main/resources/application.properties
```

Do not put real secrets in Git.

## Run

Open/import this folder as a Maven project:

```text
okta-microservices-demo
```

Run both classes:

```text
TokenClientServiceApplication
ResourceServiceApplication
```

## Test

Call the client service:

```text
GET http://localhost:8081/call-resource
```

Expected result:

```text
Resource service accepted JWT for subject: ...
```

Direct call to resource service without token should return `401`:

```text
GET http://localhost:8082/api/message
```
