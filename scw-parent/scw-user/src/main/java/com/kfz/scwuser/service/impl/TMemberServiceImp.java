package com.kfz.scwuser.service.impl;

import com.kfz.scwcommon.pojo.TMember;
import com.kfz.scwcommon.pojo.TMemberExample;
import com.kfz.scwuser.dao.TMemberMapper;
import com.kfz.scwuser.service.TMemberService;
import com.kfz.scwuser.vo.req.UserRegistVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TMemberServiceImp implements TMemberService {

    @Autowired
    private TMemberMapper tMemberMapper;

    @Override
    public void registMember(UserRegistVo vo) {
        TMember member=new TMember();
        String userpswd = vo.getUserpswd();
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        vo.setUserpswd(encoder.encode(userpswd));

        BeanUtils.copyProperties(vo,member);
        member.setUsername(vo.getLoginacct());
        member.setAuthstatus("0");
        tMemberMapper.insertSelective(member);

    }

    @Override
    public TMember login(String loginacct, String userpswd) {

        TMember tMember=null;
        TMemberExample example=new TMemberExample();
        example.createCriteria().andLoginacctEqualTo(loginacct);
        List<TMember> tMembers = tMemberMapper.selectByExample(example);
        if(tMembers.size()>0){
            tMember= tMembers.get(0);
        }
        

        return tMember;
    }
}
