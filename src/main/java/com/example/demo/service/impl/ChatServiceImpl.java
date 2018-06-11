
package com.example.demo.service.impl;

import com.example.demo.dao.ChatDao;
import com.example.demo.dto.ChatDto;
import com.example.demo.model.Chat;
import com.example.demo.service.ChatService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    ChatDao chatDaoImpl;
    
    @Override
    public Chat save(ChatDto chatDto) throws Exception {
        Chat obj = new Chat();
        Date date = new Date();
        obj.setCreated(date);
        obj.setUpdated(date);
        obj.setName(chatDto.getName());
        obj.setEmail(chatDto.getEmail());
        obj.setIpAddress(chatDto.getIp());
        obj.setStatus(false);
        Chat chat = chatDaoImpl.save(obj);
        return chat;
    }

    @Override
    public Object findAll() throws Exception {
        return chatDaoImpl.findAll(new Sort(Sort.Direction.DESC, "updated"));
    }

    @Override
    public void update(Chat chat) throws Exception {
        Chat obj = new Chat();
        obj = chatDaoImpl.findOne(chat.getChatId());
        obj.setUpdated(new Date());
        chatDaoImpl.save(obj);
    }
    
    @Override
    public void newMessage(Chat chat) throws Exception {
        Chat obj = new Chat();
        obj = chatDaoImpl.findOne(chat.getChatId());
        obj.setStatus(false);
        chatDaoImpl.save(obj);
    }

    @Override
    public void markRead(ChatDto dto) throws Exception {
        Chat chat = new Chat();
        chat = chatDaoImpl.findOne(dto.getChatId());
        chat.setStatus(true);
        chatDaoImpl.save(chat);
    }

    
    
    
    
}
