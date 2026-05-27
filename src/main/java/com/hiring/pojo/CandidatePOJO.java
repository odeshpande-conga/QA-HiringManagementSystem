package com.hiring.pojo;

/**
 * POJO class representing a Candidate entity.
 */
public class CandidatePOJO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String position;

    public CandidatePOJO() {}

    public CandidatePOJO(String firstName, String lastName, String email, String phone, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.position = position;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}

