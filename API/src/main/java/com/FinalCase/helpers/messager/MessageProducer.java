package com.FinalCase.helpers.messager;

import com.FinalCase.business.dto.UserDto;
import com.FinalCase.data.models.MessageModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Value("${sr.rabbitmq.routing.name}")
    private String routingName;
    @Value("${sr.rabbitmq.exchange.name}")
    private String exchangeName;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendToQueue(String messageModel){
        rabbitTemplate.convertAndSend(exchangeName,routingName,messageModel);
    }
}
