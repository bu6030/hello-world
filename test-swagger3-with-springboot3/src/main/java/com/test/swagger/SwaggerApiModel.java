package com.test.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description= "学生信息")
public class SwaggerApiModel implements Serializable {

    @Schema(name = "主键ID", required = true)
    private Long id;

    @Schema(name = "手机号", required = true)
    private String phonenum;

    @Schema(name = "密码", required = true)
    private String password;

    @Schema(name = "年龄", required = true)
    private Integer age;

}