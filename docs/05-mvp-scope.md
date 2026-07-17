# Step 5: MVP Scope

Full custom app, built now, client-facing only. The caregiver job-application
flow is explicitly deferred — there's no one to hire yet, and no legal
footing to employ anyone until licensing (Step 4) is complete. That work
isn't wasted by deferring it, just not useful yet.

## Public site

- Home page — services offered, service area (Seattle, Everett, King &
  Snohomish counties), founder's story/mission
- About/credentials page — founder bio, Home Care Aide certification status,
  background check confirmation, DOH license number (once issued) — directly
  answers the trust problem identified in Step 1
- Contact page — phone/email, service area

## Client request flow

Service request form capturing:

- Requestor name, phone, email, relationship to the elder (self vs. family
  member)
- Elder's name, address/zip (validated against service area)
- Type of care needed — meal prep, personal care/ADLs, mobility assistance,
  companionship (multi-select)
- Reason/trigger — post-hospital, gradual decline, respite, social (optional)
- Desired schedule — specific hours/days vs. round-the-clock, rough
  hours/week
- Urgency — ASAP / within a week / planning ahead
- Free-text notes

Confirmation screen for the submitter, plus an email notification to the
founder when a request comes in.

## Internal (admin-only) view

List of incoming requests with status (new / contacted / scheduled /
declined) and notes. Enough to not lose track of leads — not a full CRM.

## Explicitly deferred (not in MVP)

- Caregiver job application/recruiting flow
- Online scheduling/calendar booking (phone/email scheduling is fine at
  this volume)
- Payment processing (manual invoicing per Step 3)
- Login accounts, messaging, matching algorithms — unnecessary with one
  caregiver (the founder) doing manual triage

## Status: Approved
