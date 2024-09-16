package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Time;

@Entity
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String type;

    @NotBlank
    @Column(name = "notification_id")
    private Long notificationId;

    @NotBlank
    @Column(name = "notification_type")
    private String notificationType;

    @NotBlank
    @Column(columnDefinition = "CLOB")
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Time getRead_at() {
        return read_at;
    }

    public void setRead_at(Time read_at) {
        this.read_at = read_at;
    }

    private Time read_at;
}
