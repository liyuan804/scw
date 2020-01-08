package com.kfz.scwwebui.controller;

import com.kfz.scwcommon.util.AppResponse;
import com.kfz.scwwebui.service.TMemberServiceFeign;
import com.kfz.scwwebui.vo.resp.UserRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class TMemberController {
    
    @Autowired
    private TMemberServiceFeign tMemberServiceFeign;


    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("loginacct") String loginacct,
                          @RequestParam("userpswd") String userpswd, HttpSession session){
        log.debug(loginacct+"  "+userpswd+"===============");
        AppResponse<UserRespVo> login = tMemberServiceFeign.login(loginacct, userpswd);
        UserRespVo vo = login.getData();
        log.debug(login.getMsg()+"=================");
        if(vo==null){
            return "login";
        }else{
            session.setAttribute("loginacct",vo.getLoginacct());

            return "redirect:/index";
        }

    }
}
