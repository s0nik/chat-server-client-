
package com.example.demo.service;

import com.example.demo.dto.MessageDto;
import com.example.demo.model.Chat;
import com.example.demo.model.ChatMessage;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageService {
    public void saveMessage(MessageDto dto)throws Exception;
    public List<ChatMessage> getById(Chat chat)throws Exception;
}
