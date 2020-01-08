package com.kfz.scwwebui.vo.req;


import lombok.Data;


@Data
public class UserRegistVo {


    private String loginacct;


    private String userpswd;


    private String email;


    private String code;


    private String usertype;
}
