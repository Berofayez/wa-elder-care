# Step 6: Core User Flows & UX

## Flow A — Urgent (fall, hospital discharge, need help today)

Land on home page → prominent **"Call now"** phone number in the
header/hero, always visible → call directly. No form in this path — speed
wins over data capture for true emergencies.

## Flow B — Non-urgent (planning ahead, gradual decline, social/companionship interest)

Land on home page → trust signals (credentials, story) → "Request Care" →
fast, single-page form:

- Name, phone (required)
- Relationship to elder (self / family member)
- Elder's city/zip (service-area check)
- Type of need (checkboxes: personal care, meals, mobility, companionship)
- Urgency (ASAP / this week / just planning ahead)
- Optional notes

~6 fields total, mobile-first, no login required.

Submit → confirmation screen with message: **"Thanks — we'll call you back
by the end of today"** (same-business-day promise) → email notification
fires to the admin inbox.

## Flow C — Founder/admin triage

Admin gets email notification → logs into a **password-protected admin
view supporting 2 user accounts** (founder + a future second admin, same
permission level — no granular roles needed yet) → sees request list with
status (new / contacted / scheduled / declined) → calls the family same
business day → updates status and adds notes.

## Open item (non-blocking)

"Same business day" needs a concrete operational definition (days of week,
hours) before launch, since it's a promise displayed to users. Doesn't
block this step's approval — revisit before go-live.

## Status: Approved
