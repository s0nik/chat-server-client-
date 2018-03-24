package com.example.demo.controller;

import com.example.demo.dao.InfoDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.InfoDto;
import com.example.demo.model.Info;
import com.example.demo.model.User;
import com.example.demo.service.InfoService;
import com.example.demo.service.impl.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private UserService userService;
    
    @Autowired
    InfoDao infoDaoImp;

    @Autowired
    InfoService infoService;

    @GetMapping({"/", "/index"})
    public String index(ModelMap map) {
        List<Info> list = infoDaoImp.findAll();
        map.addAttribute("info", list);
        return "pages/MainPanel/index";
    }

    @GetMapping("/addinfo")
    public String addInfo() {
        return "pages/MainPanel/add_info";
    }

    @PostMapping(value = "/saveinfo")
    String saveInfo(RedirectAttributes model, @ModelAttribute InfoDto dto) {
        try {

            infoService.save(dto);
            model.addAttribute("msg", "Info Saved");
        } catch (Exception ex) {
            model.addAttribute("error", "Info Saved Failed");
        }
        return "redirect:/index";
    }

    @GetMapping(value = "/chat")
    public String chat() {
        return "pages/MainPanel/chat";
    }

    @GetMapping(value = "/server")
    public String server() {
        return "pages/MainPanel/server_chat";
    }

    @GetMapping(value = "/hey")
    public String get(){
        try {
        User user =  userService.findByUsername("admin");
        System.out.println(user.getPassword());    
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error" + e);
        }
        
        return "pages/MainPanel/chat";
        
    }
    
}
