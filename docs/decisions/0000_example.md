# ADR 0000: Use JWT for Authentication

## Status

Accepted

## Context

The application needs an authentication mechanism
for its REST APIs.

## Decision

Use JWT (JSON Web Token) for authentication.
The server will validate tokens on each request
without maintaining server-side sessions.

## Consequences

Positive:

- Stateless authentication.
- Easy horizontal scaling.
- No session storage required.

Negative:

- Token revocation is more complicated.
- Refresh token management is required.
