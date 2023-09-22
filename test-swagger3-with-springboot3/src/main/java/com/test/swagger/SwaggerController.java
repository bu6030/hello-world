package com.test.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "测试Swagger3", description = "测试Swagger3注解")
@RestController
public class SwaggerController {

    @Operation(summary = "测试Swagger3注解方法Get")
    @Parameters(@Parameter(name = "id",description = "编码"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "请求成功"),
            @ApiResponse(responseCode = "400", description = "请求参数没填好"),
            @ApiResponse(responseCode = "401", description = "没有权限"),
            @ApiResponse(responseCode = "403", description = "禁止访问"),
            @ApiResponse(responseCode = "404", description = "请求路径没有或页面跳转路径不对")
    })
    @GetMapping(value = "/swagger/student")
    public Object getStudent(@RequestParam @Parameter(example = "2")  String id){
        return id;
    }

    @Operation(summary = "测试Swagger3注解方法Post")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "请求成功"),
            @ApiResponse(responseCode = "400", description = "请求参数没填好"),
            @ApiResponse(responseCode = "401", description = "没有权限"),
            @ApiResponse(responseCode = "403", description = "禁止访问"),
            @ApiResponse(responseCode = "404", description = "请求路径没有或页面跳转路径不对")
    })
    @PostMapping(value = "/swagger/student")
    public SwaggerApiModel updateStudent(@RequestBody SwaggerApiModel model){
        return model;
    }
}
