package com.example.demo.dto;

public class LinkResponse {
    private String code;
    private String shortUrl;

    public LinkResponse() {
    }


    public LinkResponse(String code, String shortUrl){
        this.code = code;
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
