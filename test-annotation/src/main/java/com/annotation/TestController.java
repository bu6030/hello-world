package com.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * TestAnnotation注解，value为printParam
     * 打印参数
     */
    @TestAnnotation("printParam")
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public Object demo(@RequestParam String id){
        return id;
    }

    /**
     * TestAnnotation注解，value为dontPrintParam
     * 不打印参数
     */
    @TestAnnotation("dontPrintParam")
    @RequestMapping(value = "/demoNoLog", method = RequestMethod.GET)
    public Object demoNoLog(@RequestParam String id){
        return id;
    }

    /**
     * TestAnnotation注解，value为printParam
     * 打印参数
     */
    @TestAnnotation("printParam")
    @RequestMapping(value = "/demoException", method = RequestMethod.GET)
    public Object demoException(@RequestParam String id) throws Exception{
        throw new Exception();
    }

    /**
     * TestAnnotation注解，value为dontPrintParam
     * 不打印参数
     */
    @TestAnnotation("dontPrintParam")
    @RequestMapping(value = "/demoExceptionNoLog", method = RequestMethod.GET)
    public Object demoExceptionNoLog(@RequestParam String id) throws Exception{
        throw new Exception();
    }
}
