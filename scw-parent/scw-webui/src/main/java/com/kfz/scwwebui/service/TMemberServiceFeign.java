package com.kfz.scwwebui.service;

import com.kfz.scwcommon.util.AppResponse;
import com.kfz.scwwebui.service.excption.TMemberServiceFeignExceptionHanlder;
import com.kfz.scwwebui.vo.resp.UserRespVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SCW-USER")
public interface TMemberServiceFeign {

    @GetMapping("/login")
    public AppResponse<UserRespVo> login(@RequestParam("loginacct") String loginacct, @RequestParam("userpswd") String userpswd);
}
