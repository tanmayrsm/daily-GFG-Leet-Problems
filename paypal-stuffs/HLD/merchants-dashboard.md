# Merchants Dashboard (MEP / Hawk) — Overview & Capabilities

**One-line**  
MEP / Hawk is PayPal’s merchant homepage: a modular dashboard that hosts personalization, product fragments, insights, and Business Tools so merchants can manage money, activation, products and analytics in one place. :contentReference[oaicite:17]{index=17}

---

## Core capabilities (brief)
- **Dashboard zones / sections** — Personal Guidance, Quick Links, Money (remote), Recommendations, Recent Activity, Quick Actions, At-a-Glance, More-for-You. These are modular “sections” in the Hawk dashboard; many are remote modules (fragments). :contentReference[oaicite:18]{index=18}
- **Module federation / fragments** — product teams ship self-contained fragments (e.g., Subscription fragment, Money module). MEP composes these at runtime so teams can iterate without full page redeploys. :contentReference[oaicite:19]{index=19}
- **Personalization & experiments** — integrates with PStudio and EL-MO (experiments) to target content (PG cards, Grow Tiles, Kits) and run ramped rollouts and measurements. :contentReference[oaicite:20]{index=20}
- **Insights & instrumentation** — dashboards and KPIs (L1 / experience dashboards) driven by insights pipelines (GADS / Insights APIs). :contentReference[oaicite:21]{index=21}
- **Access controls & segmented rollouts** — role/permission enforcement and segmented launches via experiments and personalization. :contentReference[oaicite:22]{index=22}

---

## Business Tools (what merchants see & why it helps)
- **What it is:** an in-product hub (`/mep/merchantapps/businesstools`) where merchants **discover, set up and manage** PayPal products (invoicing, Checkout, POS/Zettle, subscriptions, financing, shipping). It is organized into:
  - **Ready to Use** — tools that work out-of-the-box (invoicing, payment links, subscriptions)
  - **Set Up / Get Started** — tools requiring activation/integration (checkout setup, POS/Zettle, loans).
  - **Kits** — curated groups (E-commerce Kit, Get Paid Kit, Launch Kits) that bundle relevant tools for a merchant persona. :contentReference[oaicite:23]{index=23} :contentReference[oaicite:24]{index=24}

**Why Business Tools helps merchants (short)**
- Single place to discover & install key PayPal capabilities.
- Faster activation (guided steps / setup wizards).
- Personalized kits and recommendations based on merchant needs and segments. :contentReference[oaicite:25]{index=25}

---

## Personalized Guidance (PG) — short summary
- **PG cards** are targeted recommendation/guidance tiles on the dashboard that surface:
  - **PRM (Product Recommendation):** promote products/features (cross-sell).
  - **NBA (Next Best Action):** nudge merchant to complete lifecycle actions (high-intent).
  - **PLG (Product Lifecycle Guide):** multi-step guided journeys / checklists.
- **How PG works (very high level):** `mepnodeweb` loads a PG federated module (`recommendationnodeweb`) → that calls `merchanthubnodeserv` (`personalizedGuidanceV2`) → merchanthub aggregates AI/ML (merchantinsightserv), PLG/EDM, PStudio content and returns normalized PG cards for rendering. :contentReference[oaicite:26]{index=26}

---

## What the Merchants Dashboard team *owns* (heavy lifting)
- **Platform & UX:** dashboard hosting, persistent header/navigation, dashboard layout and responsive UX. :contentReference[oaicite:27]{index=27}
- **Fragment hosting & module federation:** the hosting layer that loads remote modules for product teams. :contentReference[oaicite:28]{index=28}
- **Personalization & experiment integration:** PStudio/ELMO integrations and orchestration for targeted cards, tiles, and Business Tools responses. :contentReference[oaicite:29]{index=29}
- **Instrumentation & readiness:** work with GADS/insights teams for KPIs, FTUE metrics and operational readiness for launches. :contentReference[oaicite:30]{index=30}

---

## Business Tools: quick developer notes
- **Server pipeline:** `merchanthubnodeweb` calls `marketingofferreadserv` (MORS) which runs eligibility, audience targeting, strategy ranking and frequency capping to return ranked tools for ReadyToUse / Setup sections. The UI then renders tiles (ReadyToUseTile / SetupTile). :contentReference[oaicite:31]{index=31}
- **Kits & personalization:** Kits are dynamic collections; the Reboot homepage supports kits and experiment flags to modify layout/content. :contentReference[oaicite:32]{index=32}

---

## Example UX snippets (copy-ready)
- **PG card (example):** “Finish PayPal Checkout setup — enable Smart Buttons and test a purchase. [Finish setup]”
- **Business Tools tile:** “Subscriptions — Create recurring plans in a few clicks. [Create plan]”

---

## Short references
- MEP sections & how fragments are added to the dashboard. :contentReference[oaicite:33]{index=33}
- HAWK MEP resources & apps list (dev links, EL-MO). :contentReference[oaicite:34]{index=34}
- Business Tools Catalog (Ready for Action, Setup, Kits). :contentReference[oaicite:35]{index=35}
- PG HLD & developer guides (PRM / NBA / PLG). :contentReference[oaicite:36]{index=36}
- PG how-to: how PG cards are assembled in `merchanthubnodeserv`. :contentReference[oaicite:37]{index=37}
