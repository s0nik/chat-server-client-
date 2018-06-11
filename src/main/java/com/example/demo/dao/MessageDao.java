package com.example.demo.dao;

import com.example.demo.model.Chat;
import com.example.demo.model.ChatMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<ChatMessage, Integer>{

    public List<ChatMessage> findByChatId(Chat chat_id);
    
}

