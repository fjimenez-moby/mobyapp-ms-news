package com.mobydigital.academy.news.controller;

import com.mobydigital.academy.news.dto.Audience;
import com.mobydigital.academy.news.dto.NewsDto;
import com.mobydigital.academy.news.service.ContentfulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contentful")
public class ContentfulController {

    private final ContentfulService service;

    @Autowired
    public ContentfulController(ContentfulService service) {
        this.service = service;
    }

    @GetMapping("/news/app")
    public ResponseEntity<List<NewsDto>> getNewsApp() {
        List<NewsDto> news = service.buildFinalNews(Audience.MOBY_APP);
        return news == null || news.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(news);
    }

    @GetMapping("/news/web")
    public ResponseEntity<List<NewsDto>> getNewsWeb() {
        List<NewsDto> news = service.buildFinalNews(Audience.MOBY_WEB);
        return news == null || news.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(news);
    }
}
