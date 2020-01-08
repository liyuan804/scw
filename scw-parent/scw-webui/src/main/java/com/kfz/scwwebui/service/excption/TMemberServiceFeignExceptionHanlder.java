package com.kfz.scwwebui.service.excption;

import com.kfz.scwcommon.util.AppResponse;
import com.kfz.scwwebui.service.TMemberServiceFeign;
import com.kfz.scwwebui.vo.resp.UserRespVo;
import org.springframework.stereotype.Component;


@Component
public class TMemberServiceFeignExceptionHanlder implements TMemberServiceFeign {
    @Override
    public AppResponse<UserRespVo> login(String loginacct, String userpswd) {
        AppResponse<UserRespVo> fail = AppResponse.fail(null);
        fail.setMsg("登录失败！");
        return fail;
    }
}
