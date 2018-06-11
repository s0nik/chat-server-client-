package com.example.demo.controller;

import com.example.demo.dto.InfoDto;
import com.example.demo.dto.ResponseService;
import com.example.demo.service.ChatService;
import com.example.demo.service.InfoService;
import com.example.demo.service.MessageService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Restcontroller {

    @Autowired
    HttpServletRequest request;

    @Autowired
    InfoService infoService;
    ResponseService response;

    @Autowired
    ChatService chatService;

    @Autowired
    MessageService messageService;

    @GetMapping("info")
    public ResponseEntity<?> getAllInfo() {
        response = new ResponseService("All Info");
        try {
            response.setData(infoService.findAll());
        } catch (Exception ex) {
            response.setData(null);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveinfo")
    public ResponseEntity<?> save(@RequestBody InfoDto dto) {
        response = new ResponseService("Info Saved");
        try {
            infoService.save(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setErrorMessage("Info Save failed");
        }
        try {
            response.setData(infoService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deleteinfo")
    public ResponseEntity<?> delete(@RequestBody InfoDto dto) {
        response = new ResponseService("Info deleted");
        try {
            infoService.delete(dto.getId());
            response.setData(infoService.findAll());
        } catch (Exception e) {
            response.setErrorMessage("Info deletion failed");
        }
        try {
            response.setData(infoService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

}
