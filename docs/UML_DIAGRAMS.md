# 📐 UML Diagrams

## 1. Class Diagram

```
┌──────────────────────────────┐       ┌──────────────────────────────┐
│         BaseTest             │       │        URLGenerator          │
├──────────────────────────────┤       ├──────────────────────────────┤
│ # properties: Properties     │       │ - BASE_URL: String           │
│ # accessToken: String        │       │ + AUTH_REGISTER: String      │
│ # restUtils: RestUtils       │       │ + AUTH_LOGIN: String         │
│ # actorHelper: ActorHelper   │       │ + JOBS: String               │
├──────────────────────────────┤       │ + JOB_BY_ID: String          │
│ + setUpWithRole(): String    │       │ + APPLICATIONS: String       │
│ + generateAccessToken(): Str │       │ + USER_PROFILE: String       │
│ + getAdminToken(): String    │       │ + UPLOAD_RESUME: String      │
│ + getRecruiterToken(): String│       │ + ADMIN_USERS: String        │
│ + getCandidateToken(): String│       └──────────────────────────────┘
│ - loadProperties(): Props    │
└──────────────┬───────────────┘
               │ extends
┌──────────────▼───────────────┐
│         SampleTest           │
├──────────────────────────────┤
│ + testCreateJob(): void      │
│ + testGetAllJobs(): void     │
└──────────────────────────────┘

┌──────────────────────────────┐       ┌──────────────────────────────┐
│        ActorHelper           │◆─────▶│         RestUtils            │
├──────────────────────────────┤       ├──────────────────────────────┤
│ + restUtils: RestUtils       │       │ - accessToken: String        │
│ - gson: Gson                 │       ├──────────────────────────────┤
├──────────────────────────────┤       │ + post(url, body): Response  │
│ + registerUser(): Response   │       │ + get(url): Response         │
│ + loginUser(): Response      │       │ + put(url, body): Response   │
│ + createJob(): Response      │       │ + delete(url): Response      │
│ + updateJob(): Response      │       │ + patch(url, body): Response │
│ + getAllJobs(): Response     │       │ + uploadFile(): Response     │
│ + deleteJob(): Response      │       │ + replacePathParam(): String │
│ + applyForJob(): Response    │       │ - buildRequest(): ReqSpec    │
│ + getUserProfile(): Response │       └──────────────────────────────┘
│ + updateUserProfile(): Resp  │
│ + uploadResume(): Response   │
│ + getAllUsers(): Response     │
│ + deleteUser(): Response     │
└──────────────┬───────────────┘
               │ uses
┌──────────────▼───────────────────────────────────────────────────┐
│                          POJO CLASSES                              │
├────────────────┬────────────────┬────────────────┬───────────────┤
│ JobRequestPOJO │LoginRequestPOJO│RegisterRequest │ApplicationPOJO│
│ - title        │ - email        │ - email        │ - jobId       │
│ - description  │ - password     │ - password     │ - coverLetter │
│ - location     │                │ - fullName     │               │
│ - company      │+createLogin    │ - phone        │+createApp     │
│ - salary       │ Payload()      │ - role         │ Payload()     │
│ - type         │                │                │               │
│+createJobPayld │                │+createRegister │               │
└────────────────┴────────────────┴────────────────┴───────────────┘
```

## 2. Sequence Diagram — Create Job

```
Test          CommonMethod    ActorHelper    JobRequestPOJO    RestUtils    API
 │                │               │               │              │          │
 │ readTestData() │               │               │              │          │
 │───────────────▶│               │               │              │          │
 │ HashMap<S,S>   │               │               │              │          │
 │◀───────────────│               │               │              │          │
 │                │               │               │              │          │
 │        createJob(testData)     │               │              │          │
 │───────────────────────────────▶│               │              │          │
 │                │               │createJobPaylod│              │          │
 │                │               │──────────────▶│              │          │
 │                │               │  POJO object  │              │          │
 │                │               │◀──────────────│              │          │
 │                │               │               │              │          │
 │                │               │ gson.toJson() │              │          │
 │                │               │               │              │          │
 │                │               │    post(url, json)           │          │
 │                │               │─────────────────────────────▶│          │
 │                │               │               │              │POST /jobs│
 │                │               │               │              │─────────▶│
 │                │               │               │              │  201     │
 │                │               │               │              │◀─────────│
 │                │               │     Response  │              │          │
 │                │               │◀─────────────────────────────│          │
 │     Response   │               │               │              │          │
 │◀───────────────────────────────│               │              │          │
 │                │               │               │              │          │
 │ Assert(201)    │               │               │              │          │
```

## 3. Sequence Diagram — Authentication

```
Test       BaseTest       RestUtils      API Server
 │            │               │              │
 │ setUpWithRole("RECRUITER") │              │
 │───────────▶│               │              │
 │            │ loadProperties()             │
 │            │ generateAccessToken()        │
 │            │──────────────▶│              │
 │            │               │ POST /login  │
 │            │               │─────────────▶│
 │            │               │  JWT Token   │
 │            │               │◀─────────────│
 │            │  token        │              │
 │            │◀──────────────│              │
 │            │               │              │
 │            │ new RestUtils(token)          │
 │            │ new ActorHelper(restUtils)    │
 │  Ready     │               │              │
 │◀───────────│               │              │
```

## 4. Package Diagram

```
┌──────────────────────────────────────────────────────┐
│                    com.hiring                          │
│                                                       │
│  ┌──────────────┐     ┌───────────────┐             │
│  │  .tests      │────▶│   .utils      │             │
│  │ SampleTest   │     │ BaseTest      │             │
│  └──────┬───────┘     │ RestUtils     │             │
│         │             │ URLGenerator  │             │
│         │             │ TestContext   │             │
│         │             └───────┬───────┘             │
│         │                     │                      │
│         ▼                     ▼                      │
│  ┌──────────────┐     ┌───────────────┐             │
│  │  .helpers    │────▶│   .pojo       │             │
│  │ ActorHelper  │     │ JobRequest    │             │
│  └──────┬───────┘     │ LoginRequest  │             │
│         │             │ RegisterReq   │             │
│         ▼             │ ApplicationReq│             │
│  ┌──────────────┐     │ UserProfileReq│             │
│  │.commonMethods│     └───────────────┘             │
│  │ CommonMethod │                                    │
│  └──────────────┘                                    │
└──────────────────────────────────────────────────────┘
```

