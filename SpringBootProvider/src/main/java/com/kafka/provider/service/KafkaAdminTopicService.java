package com.kafka.provider.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.TopicCollection;
import org.apache.kafka.common.internals.Topic;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

@Service
public class KafkaAdminTopicService implements TopicAdminService {

  private final KafkaAdmin kafkaAdmin;

  public KafkaAdminTopicService(KafkaAdmin kafkaAdmin) {
    this.kafkaAdmin = kafkaAdmin;
  }

  @Override
  public String createTopic(String nameTopic) {
    NewTopic topic = TopicBuilder
        .name(nameTopic)
        .partitions(1)
        .replicas(2)
        .compact()
        .build();
    try{
      AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
      if(adminClient.listTopics().names().get().contains(nameTopic)){
        return nameTopic;
      }
      adminClient.createTopics(List.of(topic)).all();
      return topic.name();
    }catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new IllegalStateException("Interrupted while creating topic", e);
    } catch (ExecutionException e) {
      throw new IllegalStateException("Failed to create topic", e);
    }

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
