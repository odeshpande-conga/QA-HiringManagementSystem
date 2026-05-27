package com.hiring.pojo;

import java.util.HashMap;

/**
 * POJO for Update User Profile API request body.
 * PUT /api/users/profile
 */
public class UserProfileRequestPOJO {

    private String fullName;
    private String phone;
    private String skills;
    private String experience;

    public UserProfileRequestPOJO() {}

    /**
     * Creates and returns a UserProfileRequestPOJO populated from testData HashMap.
     * This method is called from ActorHelper.
     *
     * @param testData HashMap read from JSON test data file
     * @return populated UserProfileRequestPOJO object
     */
    public UserProfileRequestPOJO createUserProfilePayload(HashMap<String, String> testData) {
        UserProfileRequestPOJO userProfileRequestPOJO = new UserProfileRequestPOJO();
        userProfileRequestPOJO.setFullName(testData.get("fullName"));
        userProfileRequestPOJO.setPhone(testData.get("phone"));
        userProfileRequestPOJO.setSkills(testData.get("skills"));
        userProfileRequestPOJO.setExperience(testData.get("experience"));
        return userProfileRequestPOJO;
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }
}
