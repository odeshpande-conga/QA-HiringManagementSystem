package com.hiring.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class URLGenerator {

    private static final String BASE_URL;

    static {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/testdata/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
        BASE_URL = properties.getProperty("base.url");
    }

    // ==================== Authentication APIs ====================
    public static final String AUTH_REGISTER = BASE_URL + "/api/auth/register";
    public static final String AUTH_LOGIN = BASE_URL + "/api/auth/login";

    // ==================== Jobs APIs ====================
    public static final String JOBS = BASE_URL + "/api/jobs";
    public static final String JOB_BY_ID = BASE_URL + "/api/jobs/{id}";

    // ==================== Applications APIs ====================
    public static final String APPLICATIONS = BASE_URL + "/api/applications";
    public static final String MY_APPLICATIONS = BASE_URL + "/api/applications/my";
    public static final String APPLICATIONS_BY_JOB = BASE_URL + "/api/applications/job/{jobId}";
    public static final String APPLICATION_STATUS = BASE_URL + "/api/applications/{id}/status";

    // ==================== User Profile APIs ====================
    public static final String USER_PROFILE = BASE_URL + "/api/users/profile";

    // ==================== File Upload API ====================
    public static final String UPLOAD_RESUME = BASE_URL + "/api/upload/resume";

    // ==================== Admin APIs ====================
    public static final String ADMIN_USERS = BASE_URL + "/api/admin/users";
    public static final String ADMIN_USER_BY_ID = BASE_URL + "/api/admin/users/{id}";

}
