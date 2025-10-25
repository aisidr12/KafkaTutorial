package com.kafka.provider.controller;

import com.kafka.provider.dto.ProducerMessageDTO;
import com.kafka.provider.dto.TopicDTO;
import com.kafka.provider.service.TopicAdminService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0/producer")
public class ProducerController {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final TopicAdminService topicAdminService;

  public ProducerController(KafkaTemplate kafkaTemplate, TopicAdminService topicAdminService) {
    this.kafkaTemplate = kafkaTemplate;
    this.topicAdminService = topicAdminService;
  }

  @PostMapping("/send")
  public ResponseEntity<?> sendMessageToProducer(@RequestBody ProducerMessageDTO producerMessageDTO) {
    CompletableFuture<SendResult<String, String>> message = kafkaTemplate.send(
        producerMessageDTO.topicName(), producerMessageDTO.message());

    message.thenAccept(t->t.getRecordMetadata());
    System.out.println(message);

    return ResponseEntity.ok("Message Enviado");
  }
}
