package com.example.demo.controllers;

import com.example.demo.websocketmessage.Greeting;
import com.example.demo.websocketmessage.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;



@Controller
public class GreetingController {
    private final SimpMessagingTemplate messagingTemplate;
    public GreetingController(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate=messagingTemplate;
    }
    @MessageMapping("/user")
    public void greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        Greeting msg=new Greeting(HtmlUtils.htmlEscape(message.getMessage()));
        this.messagingTemplate.convertAndSendToUser(message.getReciever(),"/queue/message",msg);
    }
}
