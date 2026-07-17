# Step 7: Information Architecture

Entities the system needs to track, at a product level. Intentionally
small, matching the lean MVP scope (Step 5) — not designing for the
caregiver-hiring and scheduling features explicitly deferred to a later
phase.

## ServiceRequest

Created by the Flow B request form (Step 6):

- `id`, `created_at`
- `requestor_name`, `requestor_phone`, `requestor_email` (optional)
- `relationship` — self | family_member
- `elder_name`, `elder_city_or_zip`
- `care_types` — set of: personal_care, meals, mobility, companionship
- `urgency` — asap | this_week | planning_ahead
- `how_heard_about_us` — optional, free text or short list (marketing
  attribution)
- `notes` — free text from the requestor (optional)
- `status` — new | contacted | scheduled | declined
- `status_updated_at`
- `internal_notes` — admin-only free text (call log, triage notes)
- `handled_by` — which admin is working the request (optional)

## AdminUser

Supports Flow C's login (Step 6):

- `id`, `name`, `email`, `password` (hashed), `created_at`, `last_login_at`
- Both accounts have identical permissions — no roles/tiers, per Step 6.

## Deferred, not modeled yet

Caregiver, persistent Client records beyond a single request, and
Shift/Schedule entities belong to the caregiver-hiring and scheduling
features pushed to a later phase. Modeling them now would be designing for
a business that doesn't exist yet.

## Data sensitivity note

Elder names, addresses, and care needs are sensitive personal information
about vulnerable people. Not HIPAA-regulated (private-pay, no insurance/
Medicare billing per Step 3), but still requires: admin view must be
authenticated, `ServiceRequest` data must never be publicly readable. This
constrains hosting/database choices in Step 8.

## Status: Approved
