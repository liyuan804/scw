package com.kfz.scwuser.controller;

import com.kfz.scwcommon.util.AppResponse;
import com.kfz.scwuser.service.SmsTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Api(tags="用户登录/注册模块(包括忘记密码等)")  // 描述当前类是做什么的
@RequestMapping("/user")
@RestController
@Slf4j
public class UserLoginController {
    @Autowired
    SmsTemplate smsTemplate;

    @Autowired
    StringRedisTemplate redisTemplate;

    @ApiOperation("获取注册的验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="phoneNo",value="手机号",required=true)
    })//@ApiImplicitParams：描述所有参数；@ApiImplicitParam描述某个参数
    @PostMapping("/sendCode")
    public AppResponse<Object> sendCode(String phoneNo){
      //1、生成验证码保存到服务器，准备用户提交上来进行对比
        String code = UUID.randomUUID().toString().substring(0, 4);
     //2、保存验证码和手机号的对应关系,设置验证码过期时间
        redisTemplate.opsForValue().set(phoneNo, code, 2, TimeUnit.MINUTES);
       //2、短信发送构造参数
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phoneNo);
        querys.put("param", "code:"+code);
        querys.put("tpl_id", "TP1711063");//短信模板
       //3、发送短信
        String sendCode = smsTemplate.sendCode(querys);
        if(sendCode.equals("")||sendCode.equals("fail")){
          //短信失败
            return AppResponse.fail("短信发送失败");
        }
        return AppResponse.ok(sendCode);
    }
}
