package com.kafka.provider.controller;

import com.kafka.provider.dto.ProducerMessageDTO;
import com.kafka.provider.dto.TopicDTO;
import com.kafka.provider.service.KafkaAdminService;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
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
  private final KafkaAdminService kafkaAdminService;

  public ProducerController(KafkaTemplate kafkaTemplate, KafkaAdminService kafkaAdminService) {
    this.kafkaTemplate = kafkaTemplate;
    this.kafkaAdminService = kafkaAdminService;
  }

  @PostMapping("/create")
  public ResponseEntity<?> sendMessage(@RequestBody ProducerMessageDTO producerMessageDTO) {
    return ResponseEntity.ok(kafkaTemplate.send(producerMessageDTO.topicName(),
        producerMessageDTO.message()));
  }

  @GetMapping("/list")
  public ResponseEntity<List<String>> getMessagesInTopic()
      throws ExecutionException, InterruptedException {
    return ResponseEntity.ok(kafkaAdminService.retrieveAllTopics());
  }

  @DeleteMapping
  public ResponseEntity<?> deleteMessagesInTopic(@RequestBody TopicDTO topic) {
    kafkaAdminService.deleteTopic(topic.nameTopic());
    return ResponseEntity.ok("Delete topic");
  }


}
