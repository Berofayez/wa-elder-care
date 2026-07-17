# Step 8: Technical Architecture

Java backend chosen deliberately — the founder has an existing Spring Boot
project and reason to build familiarity with the stack. This is a
reasonable, unexciting choice for an MVP this size; the tradeoffs vs. a
Node/Next.js alternative were weighed and documented below for context.

## Stack

| Piece | Choice |
|---|---|
| Backend | Java 21 + Spring Boot 3 (Spring Web, Spring Data JPA, Spring Security) |
| Frontend | Thymeleaf server-rendered templates — one deployable app, no separate frontend build/deploy |
| Database | PostgreSQL, hosted free on Supabase |
| Auth | Spring Security, username/password, 2 admin accounts (Step 6/7) stored in Postgres |
| Build tool | Maven |
| Email | Spring Mail + Gmail SMTP (app password) — notification emails land directly in the founder's Gmail inbox |
| Hosting | Render.com free tier |
| Domain | Render's free subdomain for now; real domain purchase deferred until a business name is chosen |
| Spam protection | Honeypot field on the public request form |

Cost: **$0/month** at launch traffic levels.

## Known tradeoff

Render's free tier spins the app down after ~15 minutes of inactivity; the
next visitor after idle time eats a 30–60 second cold start. Acceptable at
solo-launch traffic, but a real UX cost for a stressed visitor. Flagged to
revisit (e.g., move to Google Cloud Run, or a low-cost Render paid tier)
if it becomes a real problem post-launch — not a blocker for MVP.

## Alternative considered (Option B, not chosen)

Next.js (TypeScript) on Vercel + Supabase Auth + Resend for email — a more
frictionless free-hosting path with no cold starts, one language across
front and back. Rejected in favor of Java/Spring Boot given the founder's
existing Spring familiarity and interest in building on that skill.

## Status: Approved
