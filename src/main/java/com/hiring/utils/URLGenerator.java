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
    public static final String AUTH_REGISTER = "/api/auth/register";
    public static final String AUTH_LOGIN = "/api/auth/login";

    // ==================== Jobs APIs ====================
    public static final String JOBS = "/api/jobs";
    public static final String JOB_BY_ID = "/api/jobs/{id}";

    // ==================== Applications APIs ====================
    public static final String APPLICATIONS = "/api/applications";
    public static final String MY_APPLICATIONS = "/api/applications/my";
    public static final String APPLICATIONS_BY_JOB = "/api/applications/job/{jobId}";
    public static final String APPLICATION_STATUS = "/api/applications/{id}/status";

    // ==================== User Profile APIs ====================
    public static final String USER_PROFILE = "/api/users/profile";

    // ==================== File Upload API ====================
    public static final String UPLOAD_RESUME = "/api/upload/resume";

    // ==================== Admin APIs ====================
    public static final String ADMIN_USERS = "/api/admin/users";
    public static final String ADMIN_USER_BY_ID = "/api/admin/users/{id}";

}
