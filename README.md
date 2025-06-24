# TAVA Auth + Multi-Tenant Module

Questo modulo fornisce le funzionalità di base di autenticazione e gestione tenant per il progetto TAVA.

## Endpoint

- `POST /auth/register` – registra un nuovo tenant e crea l'utente admin.
- `POST /auth/login` – autentica un utente e restituisce un JWT contenente `tenantId` e `role`.

## Configurazione

Esempio di `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/tava
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
jwt:
  secret: <segreto>
  expiration-ms: 86400000
```

## Avvio backend

```bash
cd backend
./gradlew bootRun
```

I test unitari si eseguono con `./gradlew test`.
