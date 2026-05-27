package com.hiring.pojo;

import java.util.HashMap;

/**
 * POJO for Create/Update Job API request body.
 * POST /api/jobs | PUT /api/jobs/{id}
 */
public class JobRequestPOJO {

    private String title;
    private String description;
    private String location;
    private String company;
    private String salary;
    private String type;

    public JobRequestPOJO() {}

    /**
     * Creates and returns a JobRequestPOJO populated from testData HashMap.
     * This method is called from ActorHelper.
     *
     * @param testData HashMap read from JSON test data file
     * @return populated JobRequestPOJO object
     */
    public JobRequestPOJO createJobPayload(HashMap<String, String> testData) {
        JobRequestPOJO jobRequestPOJO = new JobRequestPOJO();
        jobRequestPOJO.setTitle(testData.get("title"));
        jobRequestPOJO.setDescription(testData.get("description"));
        jobRequestPOJO.setLocation(testData.get("location"));
        jobRequestPOJO.setCompany(testData.get("company"));
        jobRequestPOJO.setSalary(testData.get("salary"));
        jobRequestPOJO.setType(testData.get("type"));
        return jobRequestPOJO;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
