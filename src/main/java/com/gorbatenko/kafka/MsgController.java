package com.gorbatenko.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class MsgController {

    private final KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    public MsgController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendPostc(String msg){
        kafkaTemplate.send("msg", new User("Anonimous", 100, msg));
    }

    @GetMapping
    public void sendGetData(String msg){
        kafkaTemplate.send("msg", new User("Anonimous", 100, msg));
    }

}