# Live NBA Scoreboard (TDD Project)

## Overview
This project implements a simple in-memory live NBA scoreboard library developed using **Test-Driven Development (TDD)** principles. The main goal was to design a clean, maintainable solution by writing tests first and implementing functionality incrementally.

## TDD Approach
The project follows the **Red → Green → Refactor** cycle:

- **Red:** Write failing unit tests defining the expected behavior
- **Green:** Implement the minimal code required to pass the tests
- **Refactor:** Improve code structure while keeping tests passing

Unit tests define the system behavior and serve as executable documentation.

## Features
- Start a match with an initial score of 0–0
- Update match scores
- Finish a match
- Retrieve a summary ordered by:
  - Total score (descending)
  - Most recently started match (if tied)

## Design Principles
- Test-driven design
- Clean and simple domain model
- Separation of responsibilities
- Defensive validation of inputs
- In-memory data management

## Architecture
- `LiveNBAScoreboard` – Core service managing live matches
- `Match` – Domain model representing a match
- Custom exceptions for domain validation
- In-memory storage implemented using `LinkedHashMap`

## Assumptions & Validation Rules
- Duplicate matches are rejected
- Invalid or empty team names are ignored
- Negative scores are not allowed
- Updating a non-existing match has no effect
- Finishing a non-existing match returns false

## Testing
The project includes unit tests covering:
- Core business logic
- Edge cases and validation rules
- Match lifecycle scenarios
- Score ordering logic

Tests were written before implementation to guide development.

### Run tests
```bash
mvn test
````

## Project Structure

```bash
live-NBA-scoreboard/
├── pom.xml
├── README.md
├── .gitignore
└── src
    ├── main/java/com/milad/scoreboard
    │   ├── LiveNBAScoreboard.java
    │   ├── Match.java
    │   └── exception
    │       ├── MatchAlreadyExistsException.java
    │       └── MatchNotFoundException.java
    └── test/java/com/milad/scoreboard
        ├── LiveNBAScoreboardTest.java
        └── MatchTest.java
```

## Tech Stack

* Java
* Maven
* JUnit 5
* TDD methodology

