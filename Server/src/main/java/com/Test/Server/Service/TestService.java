package com.Test.Server.Service;


import com.Test.Server.Models.Message;
import com.Test.Server.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class TestService {

    @Autowired
    MessageRepository messageRepository;

    public String addMessage(String stringMessage){
        Message message= new Message();
        message.setMessage(stringMessage);
        messageRepository.save(message);
        return "ok";
    }


    public List<Message> returnMessages(Instant startDate, Instant endDate){
        return messageRepository.getMessage(startDate,endDate);
    }
}
