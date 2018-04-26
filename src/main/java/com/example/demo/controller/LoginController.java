package com.example.demo.controller;

import com.example.demo.dto.ResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class LoginController {
    
    ResponseService response;
    
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout, ModelMap map) {
        map.addAttribute("info", error);
    return "pages/MainPanel/login";
}
    
    
}
