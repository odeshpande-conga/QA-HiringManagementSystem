# 🏗️ Architecture Diagram

## High-Level System Architecture

```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                        QA AUTOMATION FRAMEWORK                                   │
│                   Hiring Management System - API Testing                         │
├─────────────────────────────────────────────────────────────────────────────────┤
│                                                                                  │
│  ┌──────────────────────────────────────────────────────────────────────────┐   │
│  │                         TEST LAYER                                        │   │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌──────────────┐   │   │
│  │  │  AuthTests  │  │  JobsTests  │  │  AppTests   │  │ ProfileTests │   │   │
│  │  └──────┬──────┘  └──────┬──────┘  └──────┬──────┘  └──────┬───────┘   │   │
│  └─────────┼────────────────┼────────────────┼────────────────┼────────────┘   │
│            │                │                │                │                  │
│            ▼                ▼                ▼                ▼                  │
│  ┌──────────────────────────────────────────────────────────────────────────┐   │
│  │                       BASE TEST LAYER                                     │   │
│  │  ┌───────────────────────────────────────────────────────────────────┐   │   │
│  │  │  BaseTest.java                                                     │   │   │
│  │  │  • loadProperties()  • generateAccessToken()  • setUpWithRole()   │   │   │
│  │  │  • getAdminToken()   • getRecruiterToken()    • getCandidateToken()│   │   │
│  │  └───────────────────────────────────────────────────────────────────┘   │   │
│  └──────────────────────────────────┬───────────────────────────────────────┘   │
│                                     │                                            │
│                                     ▼                                            │
│  ┌──────────────────────────────────────────────────────────────────────────┐   │
│  │                     ORCHESTRATION LAYER                                   │   │
│  │  ┌───────────────────────────────────────────────────────────────────┐   │   │
│  │  │  ActorHelper.java                                                  │   │   │
│  │  │  • registerUser()    • createJob()       • applyForJob()          │   │   │
│  │  │  • loginUser()       • updateJob()       • updateApplicationStatus│   │   │
│  │  │  • getAllJobs()      • deleteJob()       • getUserProfile()        │   │   │
│  │  │  • uploadResume()   • getAllUsers()      • deleteUser()            │   │   │
│  │  └──────────────────────────┬────────────────────────────────────────┘   │   │
│  └─────────────────────────────┼────────────────────────────────────────────┘   │
│                                │                                                 │
│            ┌───────────────────┼──────────────────────┐                          │
│            ▼                   ▼                       ▼                          │
│  ┌─────────────────┐  ┌──────────────────┐  ┌───────────────────┐              │
│  │   POJO LAYER    │  │   UTILS LAYER    │  │   DATA LAYER      │              │
│  │                 │  │                  │  │                   │              │
│  │ JobRequestPOJO  │  │ RestUtils.java   │  │ CommonMethod.java │              │
│  │ LoginRequestPOJO│  │ URLGenerator.java│  │ readTestData()    │              │
│  │ RegisterRequest │  │ TestContext.java │  │ create/readExcel()│              │
│  │ ApplicationPOJO │  │                  │  │                   │              │
│  │ UserProfilePOJO │  │ • post()         │  │ ┌─────────────┐  │              │
│  │                 │  │ • get()          │  │ │  JSON Files  │  │              │
│  │ createPayload() │  │ • put()          │  │ │ config.prop  │  │              │
│  │ methods in each │  │ • delete()       │  │ │ test-data/   │  │              │
│  └────────┬────────┘  │ • patch()        │  │ └─────────────┘  │              │
│           │           │ • uploadFile()   │  └───────────────────┘              │
│           │           └────────┬─────────┘                                      │
│           │                    │                                                  │
│           └────────────────────┘                                                 │
│                                │                                                 │
├────────────────────────────────┼─────────────────────────────────────────────────┤
│                                ▼                                                 │
│  ┌──────────────────────────────────────────────────────────────────────────┐   │
│  │                    EXTERNAL SYSTEM                                         │   │
│  │  ┌──────────────────────────────────────────────────────────────────┐    │   │
│  │  │  Hiring Management System REST APIs                               │    │   │
│  │  │  https://github.com/odeshpande-conga/HiringManagementSystem       │    │   │
│  │  │                                                                    │    │   │
│  │  │  Auth │ Jobs │ Applications │ Profile │ Upload │ Admin             │    │   │
│  │  │  (16 APIs across 6 modules)                                        │    │   │
│  │  └──────────────────────────────────────────────────────────────────┘    │   │
│  └──────────────────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────────────────┘
```

## Data Flow Architecture

```
┌─────────────┐     ┌───────────────┐     ┌────────────────┐     ┌──────────────┐     ┌─────────┐
│  JSON File  │────▶│ CommonMethod  │────▶│  ActorHelper   │────▶│  RestUtils   │────▶│   API   │
│  (testdata) │     │ readTestData()│     │  createJob()   │     │   post()     │     │ Server  │
│             │     │               │     │                │     │              │     │         │
│ key:value   │     │ HashMap<S,S>  │     │ POJO→Gson→JSON │     │ HTTP Request │     │ REST    │
└─────────────┘     └───────────────┘     └────────────────┘     └──────────────┘     └────┬────┘
                                                                                           │
┌─────────────┐     ┌───────────────┐     ┌────────────────┐     ┌──────────────┐         │
│  Test Case  │◀────│  Assertions   │◀────│  ActorHelper   │◀────│  RestUtils   │◀────────┘
│  (verify)   │     │ Assert.equals │     │  return resp   │     │  Response    │
└─────────────┘     └───────────────┘     └────────────────┘     └──────────────┘
```

## Component Interaction

```
                    ┌─────────────────────────────────┐
                    │         Test Case Layer          │
                    │   extends BaseTest               │
                    │                                  │
                    │  1. Read test data (JSON)        │
                    │  2. Call actorHelper.method()    │
                    │  3. Assert response              │
                    └───────────────┬──────────────────┘
                                    │
                    ┌───────────────▼──────────────────┐
                    │         BaseTest                  │
                    │                                  │
                    │  • Load config.properties        │
                    │  • Generate JWT tokens           │
                    │  • Initialize RestUtils          │
                    │  • Initialize ActorHelper        │
                    │  • Provide TestContext            │
                    └───────────────┬──────────────────┘
                                    │
              ┌─────────────────────┼─────────────────────┐
              │                     │                     │
    ┌─────────▼────────┐  ┌────────▼────────┐  ┌────────▼────────┐
    │   ActorHelper    │  │    RestUtils    │  │  CommonMethod   │
    │                  │  │                 │  │                  │
    │  Orchestrates:   │  │  HTTP Client:   │  │  Data Provider:  │
    │  • Build payload │  │  • POST/GET/    │  │  • Read JSON     │
    │  • Call POJO     │  │    PUT/DELETE   │  │  • Read Excel    │
    │  • Gson→JSON     │  │  • Auth headers │  │  • Write Excel   │
    │  • Call RestUtils│  │  • File upload  │  │                  │
    │  • Return resp   │  │                 │  │                  │
    └────────┬─────────┘  └─────────────────┘  └──────────────────┘
             │
    ┌────────▼─────────┐
    │    POJO Layer    │
    │                  │
    │  Each POJO has:  │
    │  • Fields        │
    │  • HashMap ctor  │
    │  • createPayload │
    │  • Getters/Set   │
    └──────────────────┘
```

## Agent-Driven QA Pipeline

```
┌─────────────────────────────────────────────────────────────────────┐
│                    API AUTOMATION AGENT                               │
│                 (Main Orchestrator)                                   │
├─────────────────────────────────────────────────────────────────────┤
│                                                                      │
│  ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐     │
│  │  STEP 1  │───▶│  STEP 2  │───▶│  STEP 3  │───▶│  STEP 4  │     │
│  │Requirement│    │ TestCase │    │   POJO   │    │   Code   │     │
│  │Understanding│  │Generation│    │Evaluation│    │Generation│     │
│  └──────────┘    └──────────┘    └──────────┘    └──────────┘     │
│                                                         │           │
│                                                         ▼           │
│                                    ┌──────────┐    ┌──────────┐     │
│                                    │  STEP 6  │◀───│  STEP 5  │     │
│                                    │ Git Push │    │   Test   │     │
│                                    │ (if pass)│    │ Execution│     │
│                                    └──────────┘    └──────────┘     │
│                                                                      │
├─────────────────────────────────────────────────────────────────────┤
│  Gate Rules:                                                         │
│  • Each step must PASS before proceeding                            │
│  • Step 6 only executes if ALL tests pass                           │
│  • Full traceability: Requirement → TestCase → Code → Result        │
└─────────────────────────────────────────────────────────────────────┘
```

