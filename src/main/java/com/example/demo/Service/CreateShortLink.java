package com.example.demo.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Link;
import com.example.demo.Repository.LinkRepository;
import com.example.demo.dto.LinkResponse;

@Service
public class CreateShortLink {

    @Autowired
    LinkRepository repository;

    @Value("${app.base-url}")
    String baseUrl;

    public ResponseEntity<LinkResponse> createLink(long id, String url) {
        if (linkHasShortCode(url)) {
            Link existingLink = repository.findByOriginalUrl(url).get(0);
            String shortUrl = baseUrl + "/shrtLnk/" + existingLink.getOwnerId() + "/" + existingLink.getShortCode();
            return ResponseEntity.ok(new LinkResponse(existingLink.getShortCode(), shortUrl));
        }
        
        String shortCode = generateUniqueShortCode();
        
        Link newLink = new Link();
        newLink.setOriginalUrl(url);
        newLink.setOwnerId(id);
        newLink.setShortCode(shortCode);
        repository.save(newLink);
        String shortUrl = baseUrl + "/shrtLnk/" + id + "/" + shortCode;
        return ResponseEntity.ok(new LinkResponse(shortCode, shortUrl));
    }

    private boolean linkHasShortCode(String url) {
        return !repository.findByOriginalUrl(url).isEmpty();
    }

    private String generateUniqueShortCode() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        while (duplicatedShortUrl(code) || code.isEmpty()) {
            code.setLength(0);
            for (int i = 0; i < 10; i++) {
                code.append(chars.charAt(random.nextInt(chars.length())));
            }
        }
        return code.toString();
    }

    private boolean duplicatedShortUrl(StringBuilder code) {
        String verifyCode = code.toString();
        return repository.findByShortCode(verifyCode) != null;
    }
}
