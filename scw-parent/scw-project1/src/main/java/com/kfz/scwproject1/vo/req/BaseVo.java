package com.kfz.scwproject1.vo.req;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class BaseVo {
    private String accessToken;
}
