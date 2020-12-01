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
    // For test you need in Postman send POST-request or GET-request on url
    // http://localhost:8080/msg
    // params
    // msg=Some text

    private static KafkaTemplate<String, User> kafkaTemplateUser;

    private static KafkaTemplate<String, String> kafkaTemplateStr;

    @KafkaListener(topics="str")
    public void msgListener(String str){
        System.out.println(str);
    }

    @KafkaListener(topics="msg", containerFactory="userKafkaListenerContainerFactory")
    public void msgListener(User user){
        System.out.println(user);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(KafkaApplication.class, args);
        kafkaTemplateUser = ctx.getBean(KafkaTemplate.class);
        kafkaTemplateStr = ctx.getBean(KafkaTemplate.class);
        sendStart();
    }

    private static void sendStart() {
        kafkaTemplateUser.send("msg", new User("TestName", 25, "Hello from start app"));
        kafkaTemplateStr.send("str", "Hello from start app with STR");
    }

}