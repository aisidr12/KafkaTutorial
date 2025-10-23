package com.kafka.provider.controller;

import com.kafka.provider.dto.TopicDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
public class TopicController {

  @PostMapping("/create")
  public ResponseEntity<?> createNewTopic(@RequestBody TopicDTO topicDTO) {
    return ResponseEntity.ok(null);
  }

  @GetMapping("/list")
  public ResponseEntity<?> getTopics() {
    return null;
  }

  @DeleteMapping
  public ResponseEntity<?> deleteTopic(@RequestBody TopicDTO topic) {
    return null;
  }
}