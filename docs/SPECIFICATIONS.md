# 📋 Technical Specifications Sheet

## Project Overview

| Property | Value |
|----------|-------|
| **Project Name** | QA-HiringManagementSystem |
| **Version** | 1.0-SNAPSHOT |
| **Type** | API Test Automation Framework |
| **Purpose** | End-to-End QA Lifecycle Automation |
| **Target System** | [HiringManagementSystem](https://github.com/odeshpande-conga/HiringManagementSystem) |
| **Approach** | Agent-driven, fully automated pipeline |

---

## Technology Stack

| Category | Technology | Version |
|----------|-----------|---------|
| **Language** | Java | 11 |
| **Build Tool** | Apache Maven | 3.x |
| **Test Framework** | TestNG | 7.9.0 |
| **API Client** | RestAssured | 5.4.0 |
| **JSON Serialization** | Gson (Google) | 2.10.1 |
| **JSON Parsing** | Jackson Databind | 2.16.1 |
| **Schema Validation** | RestAssured JSON Schema Validator | 5.4.0 |
| **Excel Handling** | Apache POI | 5.2.5 |
| **Reporting** | Extent Reports | 5.1.1 |
| **Logging** | Apache Log4j 2 | 2.22.1 |
| **Build Plugin** | Maven Surefire | 3.2.5 |
| **Compiler Plugin** | Maven Compiler | 3.12.1 |
| **Exec Plugin** | Codehaus Exec Maven | 3.1.0 |

---

## API Coverage

| Module | APIs | Methods |
|--------|------|---------|
| Authentication | 2 | POST (register, login) |
| Jobs | 5 | GET, POST, PUT, DELETE |
| Applications | 4 | GET, POST, PUT |
| User Profile | 2 | GET, PUT |
| File Upload | 1 | POST (multipart) |
| Admin | 2 | GET, DELETE |
| **Total** | **16 APIs** | |

---

## Framework Architecture

### Design Patterns Used

| Pattern | Implementation |
|---------|---------------|
| **Page Object Model (adapted)** | ActorHelper acts as single point of API interaction |
| **Data-Driven Testing** | JSON files → HashMap → POJOs |
| **Builder Pattern** | POJO `createPayload()` methods |
| **Factory Pattern** | `setUpWithRole()` for token generation |
| **Singleton** | URLGenerator with static initialization |
| **Template Method** | BaseTest provides common setup/teardown |

### Layer Architecture

| Layer | Package | Responsibility |
|-------|---------|---------------|
| Test Layer | `com.hiring.tests` | Test methods with assertions |
| Base Layer | `com.hiring.utils.BaseTest` | Auth, config, lifecycle |
| Orchestration | `com.hiring.helpers.ActorHelper` | Payload building + API calls |
| HTTP Client | `com.hiring.utils.RestUtils` | Generic CRUD operations |
| Data Model | `com.hiring.pojo.*` | Request/Response POJOs |
| Endpoints | `com.hiring.utils.URLGenerator` | Centralized URL management |
| Utilities | `com.hiring.commonMethods` | JSON/Excel read/write |

---

## Project Structure

```
QA-HiringManagementSystem/
├── pom.xml                              # Maven dependencies & plugins
├── testng.xml                           # TestNG suite configuration
├── README.md                            # Project documentation
├── docs/
│   ├── ARCHITECTURE.md                  # Architecture diagrams
│   ├── UML_DIAGRAMS.md                  # Class & sequence diagrams
│   ├── FLOWCHARTS.md                    # Process flow charts
│   └── SPECIFICATIONS.md               # This file
├── .github/
│   └── agents/
│       ├── api-automation-agent.md      # Main orchestrator agent
│       └── skills/
│           ├── requirement-understanding/SKILL.md
│           ├── testcase-generation/SKILL.md
│           ├── pojo-evaluation/SKILL.md
│           ├── code-generation/SKILL.md
│           ├── test-execution/SKILL.md
│           └── git-push/SKILL.md
├── src/
│   └── main/
│       ├── java/com/hiring/
│       │   ├── commonMethods/
│       │   │   └── CommonMethod.java    # JSON/Excel utilities
│       │   ├── helpers/
│       │   │   └── ActorHelper.java     # Main orchestration class
│       │   ├── pojo/
│       │   │   ├── JobRequestPOJO.java
│       │   │   ├── LoginRequestPOJO.java
│       │   │   ├── RegisterRequestPOJO.java
│       │   │   ├── ApplicationRequestPOJO.java
│       │   │   ├── UserProfileRequestPOJO.java
│       │   │   └── CandidatePOJO.java
│       │   └── utils/
│       │       ├── BaseTest.java        # Auth + setup/teardown
│       │       ├── RestUtils.java       # Generic HTTP client
│       │       └── URLGenerator.java    # API endpoint constants
│       └── resources/
│           ├── testdata/
│           │   ├── config.properties    # Environment configuration
│           │   ├── create-job.json
│           │   ├── register-candidate.json
│           │   ├── register-recruiter.json
│           │   ├── apply-job.json
│           │   └── update-profile.json
│           ├── TestCases/
│           │   └── HiringManagementSystem.xlsx
│           └── UserStory/
│               ├── Userstory1 - HappyPath.docx
│               ├── Userstory2 - Complex Scenarios.docx
│               └── Userstory3 - End-to-End.docx
└── target/                              # Compiled output
```

---

## Agent Pipeline

| Step | Skill | Input | Output |
|------|-------|-------|--------|
| 1 | requirement-understanding | User Story (.docx) | Requirements list, API contracts |
| 2 | testcase-generation | Requirements | Test cases in Excel (.xlsx) |
| 3 | pojo-evaluation | API contracts | Java POJO classes |
| 4 | code-generation | Test cases + POJOs | TestNG test classes (.java) |
| 5 | test-execution | Test classes | Pass/Fail report |
| 6 | git-push | Passing tests | Git branch + Pull Request |

---

## Configuration

### config.properties

| Key | Description | Example |
|-----|-------------|---------|
| `base.url` | API server base URL | `http://localhost:8080` |
| `admin.email` | Admin user email | `admin@example.com` |
| `admin.password` | Admin user password | `admin123` |
| `recruiter.email` | Recruiter user email | `recruiter@example.com` |
| `recruiter.password` | Recruiter password | `recruiter123` |
| `candidate.email` | Candidate user email | `candidate@example.com` |
| `candidate.password` | Candidate password | `candidate123` |

---

## Execution Commands

| Command | Purpose |
|---------|---------|
| `mvn clean test` | Run all tests via testng.xml |
| `mvn clean test -DsuiteXmlFile=testng.xml` | Run specific suite |
| `mvn clean test -Dtest=SampleTest` | Run specific test class |
| `mvn clean compile exec:java` | Run TestCaseGenerator |

---

## Key Design Decisions

| Decision | Rationale |
|----------|-----------|
| **Gson for payload building** | Fine-grained control over JSON structure via `JsonObject` |
| **Jackson for data reading** | Efficient file-to-HashMap deserialization |
| **HashMap as data carrier** | Decouples test data format from POJO structure |
| **ActorHelper as single entry** | Abstracts API complexity; test cases stay clean |
| **BaseTest for auth only** | Separation of concerns; RestUtils handles HTTP |
| **Static URLGenerator** | Centralizes endpoint management; easy to update |
| **Exception on non-200** | Fail-fast approach ensures issues are caught immediately |
| **prettyPrint() on all responses** | Full visibility during debugging/CI |

---

## Security

| Feature | Implementation |
|---------|---------------|
| JWT Bearer Token | Automatically attached via `RestUtils.buildRequest()` |
| Role-Based Access | `setUpWithRole()` switches between ADMIN/RECRUITER/CANDIDATE |
| Token Isolation | Each test context maintains its own token |
| Config Externalized | Credentials in `config.properties` (not hardcoded) |

---

## Reporting & Logging

| Tool | Purpose |
|------|---------|
| TestNG Reports | `target/surefire-reports/` — default XML/HTML |
| Extent Reports | Rich HTML reports with screenshots (optional) |
| Log4j 2 | Application-level logging (configurable levels) |
| Console Output | `System.out.println` for payload/response visibility |

---

## Quality Gates

| Gate | Criteria |
|------|----------|
| Step 1 → Step 2 | All requirements extracted & documented |
| Step 2 → Step 3 | Test cases written & saved to Excel |
| Step 3 → Step 4 | POJOs created with `createPayload()` methods |
| Step 4 → Step 5 | Code compiles without errors |
| Step 5 → Step 6 | **ALL tests must pass** |
| Step 6 (PR) | Only created if 100% pass rate |

---

## Scalability

| Aspect | Approach |
|--------|----------|
| New APIs | Add endpoint to `URLGenerator` + method in `ActorHelper` |
| New modules | Create new POJO + new test class extending `BaseTest` |
| Multiple environments | Switch `base.url` in config.properties |
| Parallel execution | TestNG parallel suite configuration |
| CI/CD | Maven command integrates with Jenkins/GitHub Actions |

