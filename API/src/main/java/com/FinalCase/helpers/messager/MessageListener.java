package com.FinalCase.helpers.messager;

import com.FinalCase.business.dto.UserDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {


    @RabbitListener(queues = "patika-result")
    public void handleMessage(String userDto){
        //ToString
        System.out.println(userDto);
    }
}
