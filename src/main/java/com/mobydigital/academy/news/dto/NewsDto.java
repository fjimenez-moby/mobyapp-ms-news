package com.mobydigital.academy.news.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.ZonedDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
    private String id;
    private String title;
    private Boolean active;
    private String imageUrl;
    private String description;
    private Boolean isMobyWeb;
    private Boolean isMobyApp;
    private String url;
    private ZonedDateTime expirationDate;
    private ZonedDateTime createdAt;
    private Boolean priority;
}
