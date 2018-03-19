package com.example.demo.controller;

import com.example.demo.dto.ChatDto;
import com.example.demo.dto.MessageDto;
import com.example.demo.dto.ResponseService;
import com.example.demo.model.Chat;
import com.example.demo.service.ChatService;
import com.example.demo.service.MessageService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")

public class ChatController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    ChatService chatService;
    ResponseService response;

    @Autowired
    MessageService messageService;

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody ChatDto dto, HttpSession session) {
        response = new ResponseService("saved");
        try {
            dto.setIp(request.getRemoteAddr());
            Chat chat = chatService.save(dto);
            session.setAttribute("id", chat.getChatId());
            session.setAttribute("chat", chat);
            session.setMaxInactiveInterval(30 * 60); //setting session  time to 30 minute
        } catch (Exception e) {
            e.printStackTrace();
            response.setErrorMessage("failed" + e);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/message")
    public ResponseEntity<?> saveMessage(@RequestBody MessageDto dto, HttpSession session) {
        response = new ResponseService("Message not sent");
        try {
            Chat chat = (Chat) session.getAttribute("chat");
            dto.setChatId(chat);
            dto.setMessageFrom("Client");
            messageService.saveMessage(dto);
            chatService.update(chat);
            chatService.newMessage(chat);
//            response.setData(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/close")
    public ResponseEntity<?> close(HttpSession session) {
        try {
            session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/message")
    public ResponseEntity<?> getMessage(HttpSession session) throws Exception {
        ResponseService response = new ResponseService("No data");
        try {
            if (session.getAttribute("id") != null) {
                int id = (int) session.getAttribute("id");
//                Chat chat = (Chat) session.getAttribute("Chat");
                Chat chat = new Chat(id);
                chat.setChatId(id);
                response.setData(messageService.getById(chat));
                response.setMessage("Data retrieved");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);

    }

//    For Server
    @GetMapping("/allchats")
    public ResponseEntity<?> allchats() {
        ResponseService response = new ResponseService("No data");
        try {
            response.setData(chatService.findAll());
            response.setMessage("Data retrieved");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error" + e);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/servermessage")
    public ResponseEntity<?> servermessage(@RequestBody MessageDto dto) {
        ResponseService response = new ResponseService("Not Saved");
        try {
            Chat chat = new Chat();
            chat = dto.getChatId();
            dto.setMessageFrom("Server");
            messageService.saveMessage(dto);
            chatService.update(chat);
            
            
            ChatDto chatdto = new ChatDto();
            chatdto.setChatId(chat.getChatId());
            chatService.markRead(chatdto);
            
            
            response.setMessage("Messent sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }
    
    
//    It marks the message as read
    @PostMapping("/read")
    public ResponseEntity<?> read(@RequestBody ChatDto dto){
        ResponseService response =  new ResponseService("Error");
        try {
            chatService.markRead(dto);
            response.setMessage("Success");
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error");
        }
     return ResponseEntity.ok(response);   
    }
    
}
