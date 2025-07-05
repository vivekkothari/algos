# Deployment Notification System – Problem Statement & Clarified Transcript

The problem revolves around **deployment notifications** — specifically, building a system to **notify developers when
their code changes are deployed successfully for the first time**.

### 1. Notification Triggering

- Notify the **author** when their **code is deployed successfully for the first time**.
- Do **not notify again** for subsequent deployments of the same code.
- Notifications should be **per-deployment**, i.e., if 10 authors have changes in one deployment batch, each of them
  gets a **separate notification**.

### 2. Data Persistence

- You’ll need to persist deployment and author-related data to determine whether a notification should be sent.
- Assume in-memory storage unless otherwise specified.

### 3. Event Ordering and Duplicates

- You should ask whether deployment events are **guaranteed to be ordered** (e.g., START → SUCCESS/FAILURE).
- Your design must be robust against **out-of-order**, **duplicate**, or **malformed** events.
- For example, a `REVERTED` event may come **before** a `STARTED` event.

### 4. Handling Reverts

- If changes are **reverted before deployment completes**, no notification should be sent.
- If a change was deployed and then reverted later, we must **ensure the author doesn't receive a misleading
  notification**.
- You'll need to track **deployment versions**, author-change mapping, and the **revert state**.

### 5. Edge Cases to Handle

- Repeated events
- Malformed or partial data
- Duplicate authors in a batch
- Set vs List semantics in how you track authors
- Notification logic when information is incomplete

### 6. Scale & Flexibility

- Your solution should be extendable to new requirements, such as:
    - Handling reverts
    - Retry logic
    - Partial deployments
    - Future batching
- Clean abstractions, testability, and component separation (e.g., author resolution, event correlation, notifier) are
  expected.

### 7. Testing Expectations

- Be ready to write **unit tests** for edge cases like:
    - Previously notified but now reverted
    - Revert happened before success
    - Successful but not yet notified

### 8. Design Depth

- Understand the **impact of your data model** (e.g., storing authors vs versions).
- Justify whether your system is based on **eventual vs immediate notifications**, and how it handles **partial data**.
- Be ready to explain how different **event types** (e.g., STARTED, SUCCEEDED, REVERTED) affect your logic.

---

## 🧩 Problem Statement (You’re Expected to Implement or Design)

> **Design a Deployment Notification System** that:
>
> - Sends a **notification to the author(s)** of code changes **only when their changes are deployed successfully for
    the first time**.
> - Supports **multiple authors** in a single deployment batch and notifies each **individually**.
> - Handles **out-of-order, repeated, and reverted events**.
> - Avoids duplicate notifications for the same code change.
> - Tracks deployment **versions**, author mappings, and **revert states**.
> - Ensures correctness even when some events are **missing or delayed**.
> - Can scale and be extended to support additional features like:
    >
- **Deferred notifications**
>   - **Ordering guarantees**
>   - **Failure recovery**
