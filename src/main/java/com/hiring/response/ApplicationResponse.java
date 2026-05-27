package com.hiring.response;

/**
 * POJO for Application API response body.
 */
public class ApplicationResponse {

    private int id;
    private int jobId;
    private String coverLetter;
    private String status;
    private String applicantEmail;

    public ApplicationResponse() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getApplicantEmail() { return applicantEmail; }
    public void setApplicantEmail(String applicantEmail) { this.applicantEmail = applicantEmail; }
}

