package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import javax.annotation.processing.Generated;
import java.sql.Timestamp;

@Entity
public class Feedbacks {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @NotBlank
    @Column(name = "feedback_id")
    private Long feedbackId;

    @NotBlank
    @Column(name = "feedback_type")
    private Long feedbackType;

    @Column(columnDefinition = "CLOB")
    private String feedbackComment;

    @Column(columnDefinition = "NUMBER")
    private double rate;

    private Timestamp deleted_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(Long feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackComment() {
        return feedbackComment;
    }

    public void setFeedbackComment(String feedbackComment) {
        this.feedbackComment = feedbackComment;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }
}
