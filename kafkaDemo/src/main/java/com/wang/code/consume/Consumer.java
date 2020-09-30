package com.wang.code.consume;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

public class Consumer {

    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo3";

    public static class MyInterceptor implements ConsumerInterceptor<String, String>{

        @Override
        public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
            return records;
        }

        @Override
        public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
            for (OffsetAndMetadata offsetAndMetadata:offsets.values()){
                System.out.println("---------" + offsetAndMetadata.offset());
            }
        }

        @Override
        public void close() {

        }

        @Override
        public void configure(Map<String, ?> configs) {

        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("bootstrap.servers", brokerList);
        properties.put("group.id", groupId);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, MyInterceptor.class.getName());

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singleton(topic));
        kafkaConsumer.poll(Duration.ofMillis(1000));
        Set<TopicPartition> assignment = kafkaConsumer.assignment();
        for (TopicPartition topicPartition:assignment){
            kafkaConsumer.seek(topicPartition, 100);
        }



        while (true) {
            ConsumerRecords<String, String> records =
                    kafkaConsumer.poll(Duration.ofMillis(1000));
            for (TopicPartition topicPartition:assignment){
                List<ConsumerRecord<String, String>> records1 = records.records(topicPartition);
                for (ConsumerRecord<String, String> record : records1) {
                    System.out.println(record.value());
                    System.out.println(topicPartition.partition());
                }
            }
//            kafkaConsumer.commitSync();

        }

    }

}
