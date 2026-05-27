package com.hiring.pojo;

import java.util.HashMap;

/**
 * POJO for Apply Job API request body.
 * POST /api/applications
 */
public class ApplicationRequestPOJO {

    private int jobId;
    private String coverLetter;

    public ApplicationRequestPOJO() {}

    /**
     * Creates and returns an ApplicationRequestPOJO populated from testData HashMap.
     * This method is called from ActorHelper.
     *
     * @param testData HashMap read from JSON test data file
     * @return populated ApplicationRequestPOJO object
     */
    public ApplicationRequestPOJO createApplicationPayload(HashMap<String, String> testData) {
        ApplicationRequestPOJO applicationRequestPOJO = new ApplicationRequestPOJO();
        applicationRequestPOJO.setJobId(Integer.parseInt(testData.get("jobId")));
        applicationRequestPOJO.setCoverLetter(testData.get("coverLetter"));
        return applicationRequestPOJO;
    }

    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
}
