package com.kfz.scwuser.service;

import com.kfz.scwcommon.pojo.TMember;
import com.kfz.scwuser.vo.req.UserRegistVo;

public interface TMemberService {
    void registMember(UserRegistVo vo);

    TMember login(String loginacct, String userpswd);
}
