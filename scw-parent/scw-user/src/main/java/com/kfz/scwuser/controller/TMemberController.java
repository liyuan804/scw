package com.kfz.scwuser.controller;

import com.kfz.scwcommon.enums.UserExceptionEnum;
import com.kfz.scwcommon.pojo.TMember;
import com.kfz.scwcommon.util.AppResponse;
import com.kfz.scwuser.exception.MemberException;
import com.kfz.scwuser.service.TMemberService;
import com.kfz.scwuser.vo.req.UserRegistVo;
import com.kfz.scwuser.vo.resp.UserRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Api(tags = {"会员控制器"})
@Slf4j
public class TMemberController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TMemberService tMemberService;

    @ApiOperation("用户注册")
    @PostMapping("/member")
    public AppResponse regist(UserRegistVo vo){
        if(StringUtils.isEmpty(vo.getLoginacct())){
            return  AppResponse.fail(null);

        }
        String code=stringRedisTemplate.opsForValue().get(vo.getLoginacct());
        if(StringUtils.isEmpty(code)){
            return AppResponse.fail(null);
        }
        if(code.equals(vo.getCode())){

            try {
                tMemberService.registMember(vo);
                log.debug("注册成功!");
                stringRedisTemplate.delete(vo.getLoginacct());
                return AppResponse.ok(null);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("注册失败!");
                throw new MemberException(UserExceptionEnum.REGIST_FAIL);

            }

        }

        return AppResponse.fail(null);
    }


    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="loginacct",value="用户名",required=true),
            @ApiImplicitParam(name="userpswd",value="密码",required=true)
    })//@ApiImplicitParams：描述所有参数；@ApiImplicitParam描述某个参数
    @GetMapping("/login")
    public AppResponse<Object> login(@RequestParam("loginacct") String loginacct,@RequestParam("userpswd") String userpswd){

        log.debug(loginacct+"  "+userpswd+"*****************");
       BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();


        TMember tMember= tMemberService.login(loginacct,userpswd);
        if(tMember!=null){
             if(encoder.matches(userpswd,tMember.getUserpswd())){
             // if(tMember.getUserpswd().equals(userpswd)){
                 //2、登录成功;生成令牌
                 String token = UUID.randomUUID().toString().replace("-", "");
                 UserRespVo vo = new UserRespVo();
                 BeanUtils.copyProperties(tMember, vo);
                 vo.setAccessToken(token);

                 //3 将令牌存入到redis中
                 stringRedisTemplate.opsForValue().set(vo.getAccessToken(),tMember.getId().toString());

                 log.debug("===============");
                 return AppResponse.ok(vo);
             }else{
                 return AppResponse.fail(null);
             }

        }else{
            return AppResponse.fail(null);
        }


    }
}
