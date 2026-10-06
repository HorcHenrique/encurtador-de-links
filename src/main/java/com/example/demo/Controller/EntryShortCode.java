package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Service.AcessShortLink;

import jakarta.validation.Valid;


@Controller
public class EntryShortCode {
    @Autowired
    AcessShortLink acessShortLink;

    @GetMapping("shrtLnk/{id}/{shortUrl}")
    public String redirect(@Valid @PathVariable Long id, @Valid @PathVariable String shortUrl) {
        String url = acessShortLink.acess(id, shortUrl);
        if (url == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Link não encontrado");
        }
        return "redirect:" + url;
    }
}
