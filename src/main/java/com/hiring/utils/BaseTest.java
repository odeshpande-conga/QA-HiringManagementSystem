package com.hiring.utils;

import com.hiring.helpers.ActorHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * Base test class providing common setup and authentication.
 * All test classes should extend this class.
 * Use {@link RestUtils} for performing CRUD operations.
 */
public class BaseTest {

    public static Properties properties;
    public static String accessToken;
    public static RestUtils restUtils;
    public ActorHelper actorHelper;

    @BeforeClass
    public void setUp() {
        loadProperties();
        RestAssured.baseURI = properties.getProperty("base.url");

        // Generate access token using default candidate credentials
        accessToken = generateAccessToken(
                properties.getProperty("candidate.email"),
                properties.getProperty("candidate.password")
        );

        // Initialize RestUtils with the access token
        restUtils = new RestUtils(accessToken);
    }

    /**
     * Setup method to initialize RestUtils with a specific role token.
     * Call this in child test classes to override the default token.
     *
     * @param role the role: "ADMIN", "RECRUITER", or "CANDIDATE"
     */
    public String setUpWithRole(String role) {
        switch (role.toUpperCase()) {
            case "ADMIN":
                accessToken = getAdminToken();
                break;
            case "RECRUITER":
                accessToken = getRecruiterToken();
                break;
            case "CANDIDATE":
                accessToken = getCandidateToken();
                break;
            default:
                throw new RuntimeException("Invalid role: " + role + ". Use ADMIN, RECRUITER, or CANDIDATE.");
        }
        return accessToken;
    }

    @AfterClass
    public void tearDown() {
        RestAssured.reset();
    }

    // ======================== TOKEN GENERATION ========================

    /**
     * Generic method to generate an access token for any user.
     *
     * @param email    user email
     * @param password user password
     * @return JWT access token
     */
    public static String generateAccessToken(String email, String password) {
        String baseUrl = properties.getProperty("base.url");

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}")
                .when()
                .post(baseUrl + "/api/auth/login");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Login failed for user: " + email
                    + " | Status: " + response.getStatusCode()
                    + " | Response: " + response.getBody().asString());
        }

        return response.jsonPath().getString("token");
    }

    public static String getAdminToken() {
        return generateAccessToken(properties.getProperty("admin.email"), properties.getProperty("admin.password"));
    }

    public static String getRecruiterToken() {
        return generateAccessToken(properties.getProperty("recruiter.email"), properties.getProperty("recruiter.password"));
    }

    public static String getCandidateToken() {
        return generateAccessToken(properties.getProperty("candidate.email"), properties.getProperty("candidate.password"));
    }

    // ======================== HELPER METHODS ========================

    private void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/testdata/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }
}
