package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    /*
    Creacion de un topic con determinadas caracteristicas
     */


    @Bean
    public NewTopic generateTopic() {
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); // delete (borra mensaje), compact // mantiene el mas actual
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // un dia en segundos, tiempo de retencion de mensajes, por defecto viene -1, no se borra nunca
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // se pone en Bytes.. == 1 giga, tamaño max segmento
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");// tamaño maximo de cada mensaje, por defecto 1 mb

        return TopicBuilder.name("unProgramadorNace-topic")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }
}
