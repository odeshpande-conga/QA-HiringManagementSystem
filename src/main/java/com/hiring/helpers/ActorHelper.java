package com.hiring.helpers;

import com.google.gson.Gson;
import com.hiring.pojo.*;
import com.hiring.utils.RestUtils;
import com.hiring.utils.URLGenerator;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * ActorHelper - The main orchestration class for the project.
 * 
 * Responsibilities:
 * 1. Creates payloads using specific POJOs.
 * 2. Passes the payload to RestUtils to perform CRUD operations.
 * 3. Returns the Response back to the test case.
 *
 * Test classes should use ActorHelper methods instead of calling RestUtils directly.
 */
public class ActorHelper {

    public RestUtils restUtils;

    public ActorHelper(RestUtils restUtils) {
        this.restUtils = restUtils;
    }

    // ======================== AUTHENTICATION APIs ========================

    /**
     * Register a new user.
     * Calls POJO's createRegisterPayload() → converts POJO to JSON via Gson → sends to RestUtils.
     */
    public Response registerUser(HashMap<String, String> testData) {
        RegisterRequestPOJO registerRequestPOJO = new RegisterRequestPOJO();
        RegisterRequestPOJO payload = registerRequestPOJO.createRegisterPayload(testData);
        String jsonBody = new Gson().toJson(payload);
        return restUtils.post(URLGenerator.AUTH_REGISTER, jsonBody);
    }

    /**
     * Login and get JWT token.
     * Calls POJO's createLoginPayload() → converts POJO to JSON via Gson → sends to RestUtils.
     */
    public Response loginUser(HashMap<String, String> testData) {
        LoginRequestPOJO loginRequestPOJO = new LoginRequestPOJO();
        LoginRequestPOJO payload = loginRequestPOJO.createLoginPayload(testData);
        String jsonBody = new Gson().toJson(payload);
        return restUtils.post(URLGenerator.AUTH_LOGIN, jsonBody);
    }

    // ======================== JOBS APIs ========================

    /**
     * Create a new job (Recruiter only).
     * Calls POJO's createJobPayload() → converts POJO to JSON via Gson → sends to RestUtils.
     */
    public Response createJob(HashMap<String, String> testData) {
        JobRequestPOJO jobRequestPOJO = new JobRequestPOJO();
        JobRequestPOJO payload = jobRequestPOJO.createJobPayload(testData);
        String jsonBody = new Gson().toJson(payload);
        return restUtils.post(URLGenerator.JOBS, jsonBody);
    }

    /**
     * Update a job by ID.
     * Calls POJO's createJobPayload() → converts POJO to JSON via Gson → sends to RestUtils.
     */
    public Response updateJob(String jobId, HashMap<String, String> testData) {
        JobRequestPOJO jobRequestPOJO = new JobRequestPOJO();
        JobRequestPOJO payload = jobRequestPOJO.createJobPayload(testData);
        String jsonBody = new Gson().toJson(payload);
        String url = RestUtils.replacePathParam(URLGenerator.JOB_BY_ID, "id", jobId);
        return restUtils.put(url, jsonBody);
    }

    /**
     * Get all active jobs.
     */
    public Response getAllJobs() {
        return restUtils.get(URLGenerator.JOBS);
    }

    /**
     * Get job by ID.
     */
    public Response getJobById(String jobId) {
        String url = RestUtils.replacePathParam(URLGenerator.JOB_BY_ID, "id", jobId);
        return restUtils.get(url);
    }

    /**
     * Delete a job by ID.
     */
    public Response deleteJob(String jobId) {
        String url = RestUtils.replacePathParam(URLGenerator.JOB_BY_ID, "id", jobId);
        return restUtils.delete(url);
    }

    // ======================== APPLICATIONS APIs ========================

    /**
     * Apply for a job (Candidate only).
     * Calls POJO's createApplicationPayload() → converts POJO to JSON via Gson → sends to RestUtils.
     */
    public Response applyForJob(HashMap<String, String> testData) {
        ApplicationRequestPOJO applicationRequestPOJO = new ApplicationRequestPOJO();
        ApplicationRequestPOJO payload = applicationRequestPOJO.createApplicationPayload(testData);
        String jsonBody = new Gson().toJson(payload);
        return restUtils.post(URLGenerator.APPLICATIONS, jsonBody);
    }

    /**
     * Get my applications (Candidate only).
     */
    public Response getMyApplications() {
        return restUtils.get(URLGenerator.MY_APPLICATIONS);
    }

    /**
     * Get applicants for a job (Recruiter only).
     */
    public Response getApplicationsByJob(String jobId) {
        String url = RestUtils.replacePathParam(URLGenerator.APPLICATIONS_BY_JOB, "jobId", jobId);
        return restUtils.get(url);
    }

    /**
     * Update application status (e.g., PENDING, REVIEWED, SHORTLISTED, REJECTED, ACCEPTED).
     */
    public Response updateApplicationStatus(String applicationId, String status) {
        String url = RestUtils.replacePathParam(URLGenerator.APPLICATION_STATUS, "id", applicationId);
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("status", status);
        return restUtils.putWithQueryParams(url, queryParams);
    }

    // ======================== USER PROFILE APIs ========================

    /**
     * Get current user profile.
     */
    public Response getUserProfile() {
        return restUtils.get(URLGenerator.USER_PROFILE);
    }

    /**
     * Update user profile.
     * Calls POJO's createUserProfilePayload() → converts POJO to JSON via Gson → sends to RestUtils.
     */
    public Response updateUserProfile(HashMap<String, String> testData) {
        UserProfileRequestPOJO userProfileRequestPOJO = new UserProfileRequestPOJO();
        UserProfileRequestPOJO payload = userProfileRequestPOJO.createUserProfilePayload(testData);
        String jsonBody = new Gson().toJson(payload);
        return restUtils.put(URLGenerator.USER_PROFILE, jsonBody);
    }

    // ======================== FILE UPLOAD API ========================

    /**
     * Upload resume (multipart/form-data).
     */
    public Response uploadResume(String filePath) {
        return restUtils.uploadFile(URLGenerator.UPLOAD_RESUME, filePath, "resume");
    }

    // ======================== ADMIN APIs ========================

    /**
     * List all users (Admin only).
     */
    public Response getAllUsers() {
        return restUtils.get(URLGenerator.ADMIN_USERS);
    }

    /**
     * Delete a user by ID (Admin only).
     */
    public Response deleteUser(String userId) {
        String url = RestUtils.replacePathParam(URLGenerator.ADMIN_USER_BY_ID, "id", userId);
        return restUtils.delete(url);
    }
}
