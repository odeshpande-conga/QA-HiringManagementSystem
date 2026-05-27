package com.hiring.pojo;

import java.util.HashMap;

/**
 * POJO for Login API request body.
 * POST /api/auth/login
 */
public class LoginRequestPOJO {

    private String email;
    private String password;

    public LoginRequestPOJO() {}

    /**
     * Creates and returns a LoginRequestPOJO populated from testData HashMap.
     * This method is called from ActorHelper.
     *
     * @param testData HashMap read from JSON test data file
     * @return populated LoginRequestPOJO object
     */
    public LoginRequestPOJO createLoginPayload(HashMap<String, String> testData) {
        LoginRequestPOJO loginRequestPOJO = new LoginRequestPOJO();
        loginRequestPOJO.setEmail(testData.get("email"));
        loginRequestPOJO.setPassword(testData.get("password"));
        return loginRequestPOJO;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
