package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.GetAllLinksById;
import com.example.demo.dto.getAllLinksByIdResponse;

@RestController
public class GetShortCodeController {

    @Autowired
    GetAllLinksById getAllLinksById;

    @GetMapping("/get/{id}")
    public List<getAllLinksByIdResponse> getById(@PathVariable Long id) {
        return getAllLinksById.getAllLinks(id);
    }
}
