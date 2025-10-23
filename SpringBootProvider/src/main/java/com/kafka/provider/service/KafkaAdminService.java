package com.kafka.provider.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.TopicCollection;
import org.apache.kafka.common.internals.Topic;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

@Service
public class KafkaAdminService {

  private final KafkaAdmin kafkaAdmin;


  public KafkaAdminService(KafkaAdmin kafkaAdmin) {
    this.kafkaAdmin = kafkaAdmin;
  }

  public List<String> retrieveAllTopics() throws ExecutionException, InterruptedException {
    AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
    return adminClient.listTopics().names().get().stream().sorted().toList();
  }

  public void deleteTopic(final String topicName) {
    if (Topic.isValid(topicName)) {
      AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
      adminClient.deleteTopics(TopicCollection.ofTopicNames(List.of(topicName)));
    }
  }
}
