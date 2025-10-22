package com.mobydigital.academy.news.config;

import com.contentful.java.cda.CDAClient;
import com.contentful.java.cma.CMAClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContentfulConfig {

    @Value("${contentful.spaceId}")
    private String spaceId;
    @Value("${contentful.cda.accessToken}")
    private String cdaAccessToken;
    @Value("${contentful.cma.accessToken}")
    private String cmaAccessToken;
    @Value("${contentful.environment:master}")
    private String environment;


    @Bean
    public CDAClient cdaClient(){
        return CDAClient.builder()
                .setSpace(spaceId)
                .setToken(cdaAccessToken)
                .setEnvironment(environment)
                .build();
    }

    @Bean
    public CMAClient cmaClient() {
        return new CMAClient.Builder()
                .setSpaceId(spaceId)
                .setAccessToken(cmaAccessToken)
                .setEnvironmentId(environment)
                .build();
    }
}
