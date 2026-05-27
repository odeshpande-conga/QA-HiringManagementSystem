package com.hiring.pojo;

import java.util.HashMap;

/**
 * POJO for Register API request body.
 * POST /api/auth/register
 */
public class RegisterRequestPOJO {

    private String email;
    private String password;
    private String fullName;
    private String phone;
    private String role;

    public RegisterRequestPOJO() {}

    /**
     * Creates and returns a RegisterRequestPOJO populated from testData HashMap.
     * This method is called from ActorHelper.
     *
     * @param testData HashMap read from JSON test data file
     * @return populated RegisterRequestPOJO object
     */
    public RegisterRequestPOJO createRegisterPayload(HashMap<String, String> testData) {
        RegisterRequestPOJO registerRequestPOJO = new RegisterRequestPOJO();
        registerRequestPOJO.setEmail(testData.get("email"));
        registerRequestPOJO.setPassword(testData.get("password"));
        registerRequestPOJO.setFullName(testData.get("fullName"));
        registerRequestPOJO.setPhone(testData.get("phone"));
        registerRequestPOJO.setRole(testData.get("role"));
        return registerRequestPOJO;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
