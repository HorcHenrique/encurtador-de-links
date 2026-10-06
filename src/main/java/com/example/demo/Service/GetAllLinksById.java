package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Link;
import com.example.demo.Repository.LinkRepository;
import com.example.demo.dto.getAllLinksByIdResponse;

@Service
public class GetAllLinksById {

    @Autowired
    LinkRepository repository;

    @Value("${app.base-url}")
    String baseUrl;

    public List<getAllLinksByIdResponse> getAllLinks(Long id) {
        List<Link> userLinks = repository.findByOwnerId(id).stream().toList();
        List<getAllLinksByIdResponse> listToReturn = new ArrayList<>();

        for (Link link : userLinks) {
            String originalUrl = link.getOriginalUrl();
            String shortUrl = baseUrl + "/shrtLnk/" + id + "/" + link.getShortCode();
            getAllLinksByIdResponse responseToAdd = new getAllLinksByIdResponse(id, originalUrl, shortUrl);
            listToReturn.add(responseToAdd);

        }
        return listToReturn;
    }

}
