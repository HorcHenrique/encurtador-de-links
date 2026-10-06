package com.example.demo.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Link;
import com.example.demo.Repository.LinkRepository;

@Service
public class AcessShortLink {

  @Autowired
  LinkRepository repository;

  public String acess(Long ownerId, String shortUrl) {
    if (validateUrl(shortUrl)) {
      if (validatePermissionbyId(ownerId, shortUrl)) {
        Link link = catchAllLink(shortUrl);
        return acessViaNormalLink(link);
      }
    }
    return null;
  }

  private boolean validateUrl(String url) {
    return repository.findByShortCode(url) != null;
  }

  private boolean validatePermissionbyId(Long ownerId, String shortUrl) {
    Link link = catchAllLink(shortUrl);
    return link.getOwnerId().equals(ownerId);
  }

  private Link catchAllLink(String shortUrl) {
    return repository.findByShortCode(shortUrl);

  }

  private String acessViaNormalLink(Link link) {

    link.setClickCount(link.getClickCount() + 1);
    link.setLastAcessAt(LocalDateTime.now());
    repository.save(link);

    return link.getOriginalUrl();
  }
}
