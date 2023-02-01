package com.example.demo.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description= "学生信息")
public class SwaggerApiModel implements Serializable {

    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    @ApiModelProperty(value = "手机号", required = true)
    private String phonenum;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "年龄", required = true)
    private Integer age;

}