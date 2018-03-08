package com.example.demo.service.impl;

import com.example.demo.dao.MessageDao;
import com.example.demo.dto.MessageDto;
import com.example.demo.model.Chat;
import com.example.demo.model.ChatMessage;
import com.example.demo.service.MessageService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public void saveMessage(MessageDto dto) throws Exception {
        ChatMessage chat = new ChatMessage();
        chat.setChatId(dto.getChatId());
        chat.setMessage(dto.getMessage());
        chat.setMessageFrom(dto.getMessageFrom());
        chat.setTime(new Date());
        chat.setStatus(false);
        messageDao.save(chat);
    }

    @Override
    public List<ChatMessage> getById(Chat chat_id) throws Exception {
        List<ChatMessage> list = messageDao.findByChatId(chat_id);
        return list;
    }

}
