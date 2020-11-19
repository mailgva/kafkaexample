package com.gorbatenko.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@EnableKafka
@SpringBootApplication
public class KafkaApplication {

    private static KafkaTemplate<Long, String> kafkaTemplate;

    @KafkaListener(topics="msg")
    public void msgListener(String msg){
        System.out.println("message = " + msg);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(KafkaApplication.class, args);
        kafkaTemplate = ctx.getBean(KafkaTemplate.class);
        sendStart();
    }

    private static void sendStart() {
        kafkaTemplate.send("msg", 1L, "Consumer is ready");
    }

    // For test you need in postman send post request on url
    // http://localhost:8080/msg
    // params
    // msgId=1
    // msg=Some text

}
