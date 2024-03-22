package com.poc.kafkaconsumer.config;

import com.poc.kafkaconsumer.consumer.KafkaMessageListener;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.kafka.common.serialization.Serdes.*;

@Component
public class UppercaseMessageProcessor {

    private static final Serde<String> STRING_SERDE = String();
    Logger log = LoggerFactory.getLogger(UppercaseMessageProcessor.class);
    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> messageStream = streamsBuilder
                .stream("inputTopic", Consumed.with(STRING_SERDE, STRING_SERDE));

        KStream<String, String> uppercaseStream = messageStream
                .mapValues((ValueMapper<String, String>) String::toUpperCase);

        uppercaseStream.peek((key, value) -> log.info("Processing message - Key: {}, Value: {}", key, value))
                .to("outputTopic");



        //uppercaseStream.to("outputTopic");
    }
}
