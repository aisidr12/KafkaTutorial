package com.kafka.provider.controller;

import com.kafka.provider.dto.TopicDTO;
import com.kafka.provider.service.TopicAdminService;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0/topic")
public class TopicController {

  private final TopicAdminService topicAdminService;

  public TopicController(TopicAdminService topicAdminService) {
    this.topicAdminService = topicAdminService;
  }

  @PostMapping("/create")
  public ResponseEntity<String> createNewTopic(@RequestBody TopicDTO topicDTO) {
    String topic = topicAdminService.createTopic(topicDTO.nameTopic());
    return ResponseEntity.status(HttpStatus.CREATED).body("Topic created "+ topic);
  }

  @GetMapping("/list")
  public ResponseEntity<List<String>> getTopics() throws ExecutionException, InterruptedException {
    List<String> topics = topicAdminService.retrieveAllTopics();
    return ResponseEntity.ok(topics);
  }

  @DeleteMapping
  public ResponseEntity<String> deleteTopic(@RequestBody TopicDTO topic) {
    topicAdminService.deleteTopic(topic.nameTopic());
    return ResponseEntity.ok("Deleted Successfully");
  }
}