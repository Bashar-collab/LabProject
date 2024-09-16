package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Email  // Ensure email is in a valid format
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Column(unique = true)  // Unique constraint on phone number
    @Pattern(regexp = "^09\\d{8}$") // Regex for phone number that should start with 09 and should be 10 digits
    private String phone_number;

    @NotBlank
    private Date date_of_birth;
    @NotBlank
    private String gender;

    @NotBlank
    @Column(name = "profile_id")
    private Long profileId;

    @NotBlank
    @Column(name = "profile_type")
    private String profileType;

    private String profile_picture;
    @NotBlank
    private boolean verified;

    private String preferred_language;

    @NotBlank
    private boolean is_admin;

    @Column(columnDefinition = "CLOB")
    private String fcm_token;

    private Timestamp deleted_at;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getPreferred_language() {
        return preferred_language;
    }

    public void setPreferred_language(String preferred_language) {
        this.preferred_language = preferred_language;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    /*
    public Object getProfile(User user) {
        switch (user.getProfileType()) {
            case "Doctor":
                return doctorRepository.findById(user.getProfileId()).orElse(null);
            case "Nurse":
                return nurseRepository.findById(user.getProfileId()).orElse(null);
            case "Patient":
                return patientRepository.findById(user.getProfileId()).orElse(null);
            case "LabEmployee":
                return labEmployeeRepository.findById(user.getProfileId()).orElse(null);
            default:
                return null;
        }
     */
}
