package com.leogaspar.couriertracker.dto;

import java.time.LocalDateTime;

import com.leogaspar.couriertracker.entity.PackageStatus;

public class TrackingEventResponseDTO {

    private PackageStatus status;
    private String location;
    private LocalDateTime date;

    public TrackingEventResponseDTO() {}

    public TrackingEventResponseDTO(PackageStatus status, String location, LocalDateTime date) {
        this.status = status;
        this.location = location;
        this.date = date;
    }

    public PackageStatus getStatus() {
        return status;
    }

    public void setStatus(PackageStatus status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

