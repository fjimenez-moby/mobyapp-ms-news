package com.mobydigital.academy.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobydigital.academy.news.dto.NewsDto;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor

public class NotificationService {

  private final KafkaTemplate<Long, String> kafkaTemplate; // o <String,String> si cambiás a key String
  private final ObjectMapper objectMapper;                 // inyectado

  public void notifyRemoved(NewsDto dto, String newsId) throws JsonProcessingException {
    Map<String, Object> message = new HashMap<>();
    message.put("type", "REMOVED");
    message.put("id", newsId);
    message.put("item", dto);
    sendSocket(message);
    System.out.println("Notificación de REMOVED enviada para newsId: " + newsId);
  }

  public void notifyUpsert(NewsDto dto, String entryId) throws JsonProcessingException {
    Map<String, Object> message = new HashMap<>();
    message.put("type", "NEWS_UPSERT");
    message.put("id", entryId);
    message.put("item", dto);
    sendSocket(message);
  }

  private void sendSocket(Map<String, Object> message) throws JsonProcessingException {
    String json = objectMapper.writeValueAsString(message);
    // con key Long, podés mandar null o una key real
    kafkaTemplate.send("websocket", json);
  }
}
