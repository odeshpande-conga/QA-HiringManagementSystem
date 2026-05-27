package com.hiring.tests;

import com.hiring.commonMethods.CommonMethod;
import com.hiring.helpers.ActorHelper;
import com.hiring.utils.BaseTest;
import com.hiring.utils.RestUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Sample test class to validate the test setup.
 */
public class SampleTest extends BaseTest {

    public BaseTest baseTest;
    public ActorHelper actorHelperForAdmin;
    public ActorHelper actorHelperForRecruiter;
    public ActorHelper actorHelperForCandidate;

    public RestUtils restUtilsForAdmin;
    public RestUtils restUtilsForRecruiter;
    public RestUtils restUtilsForCandidate;

    public String accessTokenAdmin;
    public String accessTokenRecruiter;
    public String accessTokenCandidate;

    CommonMethod  commonMethod;

    @BeforeClass
    public void setUp() {
        baseTest = new BaseTest();
        accessTokenAdmin = baseTest.setUpWithRole("ADMIN");
        accessTokenRecruiter = baseTest.setUpWithRole("RECRUITER");
        accessTokenCandidate = baseTest.setUpWithRole("CANDIDATE");

        restUtilsForAdmin = new RestUtils(accessTokenAdmin);
        actorHelperForAdmin = new ActorHelper(restUtilsForAdmin);

        restUtilsForRecruiter = new RestUtils(accessTokenRecruiter);
        actorHelperForRecruiter = new ActorHelper(restUtilsForRecruiter);

        restUtilsForCandidate = new RestUtils(accessTokenCandidate);
        actorHelperForCandidate = new ActorHelper(restUtilsForCandidate);

    }

    @Test(groups = {"Smoke"}, description = "Post New Job")
    public void postNewJob() throws Exception {
        HashMap<String, String> testData = CommonMethod.readTestData("src/main/resources/testdata/create-job.json");
        Response response = actorHelperForRecruiter.createJob(testData);
        Assert.assertEquals(response.getStatusCode(), 200, "Job creation should return 201");
        System.out.println("Job Created: " + response.getBody().asString());
    }




}

