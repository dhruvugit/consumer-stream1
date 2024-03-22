package com.poc.kafkaconsumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.util.Locale;
import java.util.Properties;

//@Configuration
@EnableKafkaStreams
public class StringProcessor {

    @Bean
    public KafkaStreams kafkaStreams(StreamsBuilder streamsBuilder) {
        Properties props = getConfig();

        KStream<String, String> stream = streamsBuilder.stream("inputTopic");
        stream.mapValues(value -> value.toUpperCase(Locale.ENGLISH))
                .to("outputTopic", Produced.with(Serdes.String(), Serdes.String()));

        return new KafkaStreams(streamsBuilder.build(), props);
    }

    private Properties getConfig() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "uppercase-app67");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, "0");

        return properties;
    }

}
