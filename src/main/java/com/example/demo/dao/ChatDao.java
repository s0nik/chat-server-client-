
package com.example.demo.dao;

import com.example.demo.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatDao extends JpaRepository<Chat, Integer>{
    
public Chat findByChatId(int chat_id);
}
