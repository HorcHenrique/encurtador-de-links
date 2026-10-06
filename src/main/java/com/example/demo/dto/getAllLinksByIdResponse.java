package com.example.demo.dto;

public class getAllLinksByIdResponse {
    private final Long OwnerId;
    private final String originalUrl;
    private final String ShortUrl;


    public getAllLinksByIdResponse(Long OwnerID, String originalUrl, String shortUrl){
        this.OwnerId =  OwnerID;
        this.originalUrl = originalUrl;
        this.ShortUrl = shortUrl;
    }

    public Long getOwnerId() {
        return OwnerId;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrl() {
        return ShortUrl;
    }
}
