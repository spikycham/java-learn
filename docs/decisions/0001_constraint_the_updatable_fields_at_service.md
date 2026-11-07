# ADR 0001: Constraint the Updatable Fields at Service

## Status

Accepted

## Context

The fields of an update should be constrianed to some specific ones,
don't let the user update every field. Service layer is the business
logic home, so whitelist belongs there.

## Decision

Constraint updatable fields at service layer via whitelist validation,
keep the repository thin and dumb.

## Consequences

Positive:

- Responsibility clear per layer.
- Repository layer simpler.
- Whitelist single source of truth.

Negative:

- Can't constrain at repo layer if service bypassed.

## Alternatives Considered

- Constraint at repo layer: rejected, mixes business logic into SQL layer
- Constraint at controller: rejected, controllers should be thin, logic may be called by other services later
- DB-level constraint (CHECK/trigger): rejected, complex field whitelists = ugly SQL, harder to change

## Additional

But the JSON schema should be validated at controller layer,
and the service layer always trusts the controller.
