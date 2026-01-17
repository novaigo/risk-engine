# Risk Engine

A general-purpose risk engine for scoring and decision-making based on configurable rules.

This engine implements a flexible **filter → score → decision** pattern that can be applied across many domains. Example applications include:

- **Fraud detection** (financial transactions, orders, gift cards)
- **Underwriting automation** (insurance, credit risk)
- **Risk scoring** (transactions, claims, devices)
- **Alert prioritization** (healthcare, operations)

**Note:** In this repository, the engine is presented as a **transaction risk scoring engine** for financial transactions as a concrete example.

---

## Overview

The engine consists of two main flows:

1. **Bootup Flow** – configuration, rule construction, and registry initialization [View/edit PlantUML source](https://editor.plantuml.com/uml/RKz1JWCn3Bpd5LPFS2WFu80g8I5nG-K59lKkLfCuSXpGWlWxoVP2b-Jap8ndneTYg5PZSCOM2Dv4h6QOWdmwbz6VSIROFKgQUAwAnf9syEsWFIynIw9aW3a7zYjvjyImOOzSpiSQgBoeP59ZAkxdZtdde2KKq-h70LrowV24taIN0L-U4njZu2_IZNyoZWIlXaPZxoFDtEANlhSv1lRxX-490wnh1rtTFsWLRal3FMIL3pvHMJdWL0oJxrbRQTk_AjvsTHr9RxJXjyubCGBb-Tr0fYlnnGvG2RmI6fr0Mep3T89YektF2IB5NfuQsAtSeKqrXby0)  
   ![Bootup Flow](http://www.plantuml.com/plantuml/svg/RL51QWCn3Bpx5IBtz0abK2ZqLlO7nb6s8cl9oF9XM_htQhUmuCG-sJEQqGo-L8jgBI-Xn7IFCy8f2TzeRXgDX4_m4y1Fabo4aGrYAGkbdNnPNTUp4zNxr1Qidoe5rGXh-0sTUJLjQUozAT_szUyncUWwuEi7ar5Sw1krizR6HXd16ncElIUSoTUlc-wX1vpFhyFq0-n_zYbhU8AEmIxq8t1C7V42Yo4epLy6SkkvNGrH4PAYDxs2SC8G-UhDHVrD31dpbWWTtAp2nM_-oNy0)

2. **Runtime Flow** – transaction evaluation, rule scoring, and decision-making [View/edit PlantUML source](https://editor.plantuml.com/uml/bP91QWCn34NtFeN9FbSeWRONI7e1rHL6n2CPIIw4qhktaqM8Cvx0U2VvlszzeuCvgBUfX0hf31dZ3Yhjua-8_oV9L8MHFNuei45o4duLTfLIKCDlkCTgeGIp8XQue2vDZcJdKn8bpYVKRqhOEMXZfmcZEJWEw5RmY9dCzT9ndys8qImMoPJ6e3tqtZ2HaV0xP-9-36bUcwzFVrKF_oFkzo-ZaClMhEm3tHUtMtk6sfMnROy-1k_BRLutvQfWl3NXWFmrhzmV)  
   ![Runtime Flow](http://www.plantuml.com/plantuml/svg/bP9H2i8m38RVUugozrr11FK2cnUeDPImBXrDAeXuT-i4TLidcBUa_vVc3zcoc2YfzsemjZCET6K6hFHDwHmsz4CW8D77Q8YD5Goq2oGnU0zHtTK7OuWNjB146-IkjI4YkVQbcN0naM0FEWyY9JfvQC0XIxnEk5DY963MFZYqPN1MseD5pWuEv38wwHQI34dMXn_LnINekjwKFAfbxINzDlITl5z_eYFoxvypuzFqRmFlbQm8ohUYja3dvw4z0000)

---

## Features

- configurable rules via `application.yml`
- In-memory rule registry for fast access
- Orchestrated scoring with explainable outputs 

---

## Running Locally

```bash
mvn clean install
mvn spring-boot:run
```

### transaction test
```json
curl -X POST http://localhost:<port>/transactions/score \
-H "Content-Type: application/json" \
-d '{
"amount": 900,
"country": "AT",
"velocity": 1,
"timestamp": "2026-01-16T02:30:00",
"timezone": "UTC"
}'
```

