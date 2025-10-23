package com.kafka.provider.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/***
 *  Proveedor configuración -- se encarga de enviar los mensajes
 */
@Configuration
public class KafkaProviderConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  /**
   * this is a configuration how would be sent the data
   *
   * @return
   */
  public Map<String, Object> producerConfig() {
    Map<String, Object> properties = new HashMap<>();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrapServers); //Indicamos el servidor de kafka
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class); // String key converter serializer from provider
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class); // String value converter serializer from provider
    return properties;
  }

  /**
   * This is the client provider factory
   *
   * @return new Defaultkafka, create a new provider.
   */
  @Bean
  public ProducerFactory<String, String> providerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfig());
  }

  /***
   * This is the object who sent the messages through kafkaTemplate
   * @param producerFactory
   * @return
   */
  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(
      ProducerFactory<String, String> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }
}
