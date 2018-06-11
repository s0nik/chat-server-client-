package com.example.demo.service;

import com.example.demo.dto.ChatDto;
import com.example.demo.model.Chat;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatService {
    public Chat save(ChatDto chatDto)throws Exception;
    public Object findAll()throws Exception;
    public void update(Chat chat)throws Exception;
    public void newMessage(Chat chat)throws Exception;
    public void markRead(ChatDto dto)throws Exception;
}
