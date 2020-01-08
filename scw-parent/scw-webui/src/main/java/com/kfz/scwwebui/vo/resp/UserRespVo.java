package com.kfz.scwwebui.vo.resp;


import lombok.Data;

@Data

public class UserRespVo {


    private String accessToken;//访问令牌

    private String loginacct; //存储手机号
    private String username;
    private String email;
    private String authstatus;
    private String usertype;
    private String realname;
    private String cardnum;
    private String accttype;
}
