package com.wang.code.product;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class Producer {

    public static final String topic = "topic-demo";
    public static final String brokerList = "localhost:9092";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);

        try (KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties)) {

            for (int i = 0; i < 5; i++){
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "hello", "hello Kafka!" + i);
                Future<RecordMetadata> send = kafkaProducer.send(producerRecord, (metadata, exception) -> {
                    if (exception != null){
                        exception.printStackTrace();
                    } else {
                        System.out.println(metadata.partition());
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
