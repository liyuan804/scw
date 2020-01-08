package com.kfz.scwuser.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class User {
    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

}
