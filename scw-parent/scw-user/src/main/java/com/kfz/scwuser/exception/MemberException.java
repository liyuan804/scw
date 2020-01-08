package com.kfz.scwuser.exception;

import com.kfz.scwcommon.enums.UserExceptionEnum;

public class MemberException extends  RuntimeException {

    public MemberException(){}

    public MemberException(UserExceptionEnum exceptionEnum){
        super(exceptionEnum.getMsg());
    }

}
