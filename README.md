# WA Elder Care (placeholder name)

A web platform for an in-home elder care agency based in Washington State.
The founder is the primary caregiver at launch, with a path to hiring/contracting
additional caregivers as the business grows. Clients (or their family members)
request care services through the platform; caregivers apply to work for the
agency through the same platform.

This repo is the planning record for the product, written before any code is
written. Each step in `docs/` is drafted, discussed, and approved before the
next one starts.

## Status

Planning complete (all 9 steps approved). MVP built per
`docs/08-technical-architecture.md`: Java 21 + Spring Boot 4, Thymeleaf,
PostgreSQL, Spring Security. See `docs/00-planning-process.md` for the
roadmap and `docs/` for each step's decisions.

## Running locally

Requires Java 21+ and Maven.

```
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

The `local` profile uses an H2 file database (`./data/local-db`, gitignored)
instead of Postgres, and seeds one admin account:
`admin@example.com` / `dev-password-123`. Visit `http://localhost:8080`.
Email sending isn't configured in this profile — new-request notifications
will log an error and be skipped, without blocking the request from saving.

## Running the tests

```
mvn test
```

## Production configuration

No profile is active in production — all configuration comes from
environment variables (see `src/main/resources/application.yml`):

| Variable | Purpose |
|---|---|
| `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` | Postgres connection (Supabase, per Step 8) |
| `MAIL_USERNAME`, `MAIL_PASSWORD` | Gmail SMTP app password for sending notification emails |
| `NOTIFY_EMAIL` | Inbox that receives new-request notifications |
| `BUSINESS_NAME`, `BUSINESS_PHONE` | Displayed across the site |
| `ADMIN1_NAME`, `ADMIN1_EMAIL`, `ADMIN1_PASSWORD`, `ADMIN2_NAME`, `ADMIN2_EMAIL`, `ADMIN2_PASSWORD` | The two admin accounts (Step 6/7) — seeded on startup if not already present |
| `PORT` | Provided automatically by Render |

Deploy target: Render.com free tier, per Step 8.

## Planning steps

1. [Vision & problem definition](docs/01-vision-and-business-model.md)
2. [Market & user segments](docs/02-market-and-user-segments.md)
3. [Business model](docs/03-business-model.md)
4. [Trust, safety & compliance](docs/04-trust-safety-compliance.md)
5. [MVP scope](docs/05-mvp-scope.md)
6. [Core user flows & UX](docs/06-core-user-flows-ux.md)
7. [Information architecture](docs/07-information-architecture.md)
8. [Technical architecture](docs/08-technical-architecture.md)
9. [Launch & roadmap](docs/09-launch-and-roadmap.md)
