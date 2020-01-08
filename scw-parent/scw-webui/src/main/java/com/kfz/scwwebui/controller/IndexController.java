package com.kfz.scwwebui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){

        return "index";
    }


    @RequestMapping("/showLogin")
    public String showLogin(){
        return "login";
    }


}
