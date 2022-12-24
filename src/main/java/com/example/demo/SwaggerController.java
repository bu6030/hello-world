package com.example.demo;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = "测试Swagger3", description = "测试Swagger3注解")
@RestController
public class SwaggerController {

    @ApiOperation(value = "测试Swagger3注解方法Get")
    @ApiImplicitParams(@ApiImplicitParam(name = "id",value = "编码"))
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 401, message = "没有权限"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @GetMapping(value = "/swagger/student")
    public Object getStudent(@RequestParam @ApiParam(defaultValue = "1",example = "2")  String id){
        return id;
    }

    @ApiOperation(value = "测试Swagger3注解方法Post")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 401, message = "没有权限"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @PostMapping(value = "/swagger/student")
    public SwaggerApiModel updateStudent(@RequestBody SwaggerApiModel model){
        return model;
    }
}
