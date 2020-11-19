package com.gorbatenko.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class MsgController {

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @PostMapping
    public void sendOrder(Long msgId, String msg){
        kafkaTemplate.send("msg", msgId, msg);
    }

}