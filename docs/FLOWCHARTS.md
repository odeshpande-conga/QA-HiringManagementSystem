# 🔄 Flow Charts

## 1. End-to-End QA Lifecycle Flow

```
┌─────────────────────────────────────────────────────────────────┐
│                    QA AUTOMATION LIFECYCLE                        │
└─────────────────────────────────────────────────────────────────┘

    ┌───────────────────┐
    │   USER STORY /    │
    │   REQUIREMENT     │
    │   INPUT           │
    └────────┬──────────┘
             │
             ▼
    ┌───────────────────┐
    │  STEP 1:          │     Read .docx/.doc files
    │  Requirement      │────▶ Extract API contracts
    │  Understanding    │     Identify acceptance criteria
    └────────┬──────────┘
             │
             ▼
    ┌───────────────────┐
    │  STEP 2:          │     Generate positive test cases
    │  Test Case        │────▶ Generate negative test cases
    │  Generation       │     Generate edge cases
    └────────┬──────────┘     Write to Excel (.xlsx)
             │
             ▼
    ┌───────────────────┐
    │  STEP 3:          │     Evaluate existing POJOs
    │  POJO             │────▶ Create new POJOs if needed
    │  Evaluation       │     Add createPayload() methods
    └────────┬──────────┘
             │
             ▼
    ┌───────────────────┐
    │  STEP 4:          │     Generate test class files
    │  Code             │────▶ Use ActorHelper pattern
    │  Generation       │     Follow BaseTest inheritance
    └────────┬──────────┘
             │
             ▼
    ┌───────────────────┐
    │  STEP 5:          │     mvn clean test
    │  Test             │────▶ Validate all tests pass
    │  Execution        │     Generate test reports
    └────────┬──────────┘
             │
             ▼
        ┌────────────┐
        │ ALL TESTS  │
        │   PASS?    │
        └─────┬──────┘
              │
     ┌────────┼────────┐
     │ YES    │        │ NO
     ▼        │        ▼
┌──────────┐  │  ┌──────────────┐
│  STEP 6: │  │  │ FIX & RETRY  │
│  Git Push│  │  │ Go to Step 4 │
│  Create  │  │  └──────────────┘
│  PR      │  │
└──────────┘  │
              │
              ▼
    ┌───────────────────┐
    │   PULL REQUEST    │
    │   READY FOR       │
    │   REVIEW          │
    └───────────────────┘
```

## 2. Test Execution Flow

```
                         ┌────────────────────┐
                         │   mvn clean test   │
                         └─────────┬──────────┘
                                   │
                         ┌─────────▼──────────┐
                         │  Load testng.xml   │
                         └─────────┬──────────┘
                                   │
                         ┌─────────▼──────────┐
                         │  @BeforeClass       │
                         │  setUp() / BaseTest │
                         └─────────┬──────────┘
                                   │
              ┌────────────────────┼────────────────────┐
              │                    │                    │
    ┌─────────▼────────┐ ┌────────▼────────┐ ┌────────▼────────┐
    │ loadProperties() │ │ generateToken() │ │ init RestUtils  │
    │ config.properties│ │ POST /auth/login│ │ + ActorHelper   │
    └─────────┬────────┘ └────────┬────────┘ └────────┬────────┘
              │                    │                    │
              └────────────────────┼────────────────────┘
                                   │
                         ┌─────────▼──────────┐
                         │   @Test methods    │
                         │  (run sequentially)│
                         └─────────┬──────────┘
                                   │
              ┌────────────────────┼────────────────────┐
              │                    │                    │
    ┌─────────▼────────┐ ┌────────▼────────┐ ┌────────▼────────┐
    │ Read test data   │ │ Call ActorHelper │ │  Assert Response │
    │ (JSON → HashMap) │ │ (build & send)  │ │  (status + body) │
    └──────────────────┘ └─────────────────┘ └──────────────────┘
                                   │
                         ┌─────────▼──────────┐
                         │  @AfterClass       │
                         │  tearDown()        │
                         │  RestAssured.reset()│
                         └─────────┬──────────┘
                                   │
                         ┌─────────▼──────────┐
                         │  Test Reports      │
                         │  (TestNG + Extent) │
                         └────────────────────┘
```

## 3. Request Processing Flow

```
┌────────────────────────────────────────────────────────────────────────────┐
│                        REQUEST PROCESSING PIPELINE                           │
└────────────────────────────────────────────────────────────────────────────┘

┌─────────────┐    ┌──────────────┐    ┌─────────────┐    ┌──────────────┐
│   Step 1    │    │   Step 2     │    │   Step 3    │    │   Step 4     │
│             │    │              │    │             │    │              │
│  JSON File  │───▶│ CommonMethod │───▶│ ActorHelper │───▶│  POJO Layer  │
│             │    │              │    │             │    │              │
│ testdata/   │    │readTestData()│    │createJob()  │    │createJobPay  │
│ create-job  │    │             │    │             │    │load(testData)│
│ .json       │    │ Returns:    │    │ Receives:   │    │              │
│             │    │HashMap<S,S> │    │HashMap<S,S> │    │ Returns:     │
│ {           │    │             │    │             │    │ POJO object  │
│  "title":.. │    └──────────────┘    └─────────────┘    └──────┬───────┘
│  "desc":..  │                                                   │
│ }           │                                                   ▼
└─────────────┘                                          ┌──────────────┐
                                                         │   Step 5     │
                                                         │              │
                                                         │  Gson.toJson │
                                                         │  (POJO→JSON) │
                                                         │              │
                                                         │  Returns:    │
                                                         │  JSON String │
                                                         └──────┬───────┘
                                                                │
┌─────────────┐    ┌──────────────┐    ┌─────────────┐         │
│   Step 8    │    │   Step 7     │    │   Step 6    │         │
│             │    │              │    │             │◀────────┘
│  Test Case  │◀───│ ActorHelper  │◀───│  RestUtils  │
│             │    │              │    │             │
│Assert.equals│    │Returns resp  │    │post(url,json)│
│(statusCode) │    │prettyPrint() │    │             │
│             │    │check status  │    │Adds headers:│
└─────────────┘    └──────────────┘    │Content-Type │
                                       │Authorization│
                                       │Bearer token │
                                       └──────┬──────┘
                                              │
                                              ▼
                                       ┌─────────────┐
                                       │  HTTP POST  │
                                       │  to API     │
                                       │  Server     │
                                       └─────────────┘
```

## 4. Authentication & Token Management Flow

```
┌───────────────────────────────────────────────────────────────────┐
│                    TOKEN MANAGEMENT FLOW                            │
└───────────────────────────────────────────────────────────────────┘

         ┌──────────────────────────┐
         │    config.properties     │
         │                          │
         │  base.url=http://...     │
         │  admin.email=...         │
         │  admin.password=...      │
         │  recruiter.email=...     │
         │  recruiter.password=...  │
         │  candidate.email=...     │
         │  candidate.password=...  │
         └────────────┬─────────────┘
                      │
                      ▼
         ┌──────────────────────────┐
         │   setUpWithRole(role)    │
         │                          │
         │  switch(role):           │
         │    ADMIN → getAdminToken │
         │    RECRUITER → getRecr.. │
         │    CANDIDATE → getCand.. │
         └────────────┬─────────────┘
                      │
                      ▼
         ┌──────────────────────────┐
         │  generateAccessToken()   │
         │                          │
         │  POST /api/auth/login    │
         │  Body: {email, password} │
         └────────────┬─────────────┘
                      │
                      ▼
         ┌──────────────────────────┐
         │  Extract JWT from        │
         │  response.data.token     │
         └────────────┬─────────────┘
                      │
           ┌──────────┼──────────┐
           │          │          │
           ▼          ▼          ▼
    ┌───────────┐ ┌────────┐ ┌───────────┐
    │ RestUtils │ │ Actor  │ │   Test    │
    │(token set)│ │ Helper │ │  Context  │
    └───────────┘ └────────┘ └───────────┘
```

## 5. Error Handling Flow

```
         ┌──────────────────────────┐
         │   ActorHelper Method     │
         │   e.g., createJob()      │
         └────────────┬─────────────┘
                      │
                      ▼
         ┌──────────────────────────┐
         │  Build payload via POJO  │
         │  Convert to JSON (Gson)  │
         │  Print: "Job Payload:..."│
         └────────────┬─────────────┘
                      │
                      ▼
         ┌──────────────────────────┐
         │  Call RestUtils.post()   │
         │  Get Response            │
         │  response.prettyPrint()  │
         └────────────┬─────────────┘
                      │
                      ▼
         ┌──────────────────────────┐
         │  Status Code == 200?     │
         └─────┬──────────────┬─────┘
               │              │
          YES  │              │  NO
               ▼              ▼
    ┌───────────────┐  ┌─────────────────┐
    │ Return        │  │ throw Exception │
    │ Response      │  │ ("Failure")     │
    │ to Test Case  │  │                 │
    └───────────────┘  │ Test FAILS      │
                       │ immediately     │
                       └─────────────────┘
```

