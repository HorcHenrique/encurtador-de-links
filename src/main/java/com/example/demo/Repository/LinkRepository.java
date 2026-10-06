package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Link;
import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {

    List<Link> findByOriginalUrl(String originalUrl);

    Link findByShortCode(String shortCode);

    List<Link> findByOwnerId(Long ownerId);
}
