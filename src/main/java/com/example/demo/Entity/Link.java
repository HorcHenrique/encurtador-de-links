package com.example.demo.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "original_url", nullable = false, length = 2048)
    @URL
    private String originalUrl;
    @Column(name = "short_code", nullable = false, unique = true, length = 32)
    private String shortCode;
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;
    @Column(name = "created_date")
    private LocalDate cratedDate;
    @Column(name = "click_count", nullable = false)
    private Long clickCount = 0L;
    @Column(name = "last_access_at")
    private LocalDateTime lastAcessAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getCratedDate() {
        return cratedDate;
    }

    public void setCratedDate(LocalDate cratedDate) {
        this.cratedDate = cratedDate;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getLastAcessAt() {
        return lastAcessAt;
    }

    public void setLastAcessAt(LocalDateTime lastAcessAt) {
        this.lastAcessAt = lastAcessAt;
    }

    public String getShortCode() {
        return shortCode;
    }

    @PrePersist
    protected void onCreate() {
        if (clickCount == null) {
            clickCount = 0L;
        }
        if (cratedDate == null) {
            cratedDate = LocalDate.now();
        }
        if (lastAcessAt == null) {
            lastAcessAt = LocalDateTime.now();
        }
    }


}
