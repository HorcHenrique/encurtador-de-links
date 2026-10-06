package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.CreateShortLink;
import com.example.demo.dto.CreateLinkRequest;
import com.example.demo.dto.LinkResponse;

import jakarta.validation.Valid;

@RestController
public class CreateLinkController {

    @Autowired
    CreateShortLink createShortLinkService;

    @PostMapping("/create")
    public ResponseEntity<LinkResponse> createShortLink(@Valid @RequestBody CreateLinkRequest request) {
        return createShortLinkService.createLink(request.getOwnerId(), request.getUrl());
    }





}
