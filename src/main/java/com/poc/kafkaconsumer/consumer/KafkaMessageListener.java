package com.poc.kafkaconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "outputTopic",groupId = "testgroup556")
    public void consume(String message){
        log.info("consumer1 consume the message {} ", message);
    }
//    @KafkaListener(topics = "testtopic100",groupId = "testgroup")
//    public void consume2(String message){
//        log.info("consumer2 consume the message {} ", message);
//    }
//    @KafkaListener(topics = "testtopic100",groupId = "testgroup")
//    public void consume3(String message){
//        log.info("consumer3 consume the message {} ", message);
//    }
//    @KafkaListener(topics = "testtopic100",groupId = "testgroup")
//    public void consume4(String message){
//        log.info("consumer4 consume the message {} ", message);
//    }


}

//For better throughput we make multiple instances of our consumer, and if we are using only
//one consumer then also we have to mention the group id id in @KafkaListner
//and also mention this into the applicatiin propeties fiels
// this will help out consumer group to find the task he have to do (listen to which topic partition)



