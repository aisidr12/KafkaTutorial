package com.kafka.provider.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface TopicAdminService {

  String createTopic(String nameTopic);

  List<String> retrieveAllTopics() throws ExecutionException, InterruptedException;

  void deleteTopic(final String topicName);
}
