package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerLogAspect {

    //设定PointCut也就是指定切入点位置
    //注意execution表达式中的第一个*代表返回的类型，咱们用*表示任何，可以指定具体返回类型
    //execution表达式中第二部分为具体的类名方法名，可以写具体包名也可以通过*等匹配
    //execution表达式中括号内部表示请求参数..表示不限定也可以指定具体类型参数
    //@Pointcut中的execution可以通过||,&&等符号匹配表示或/且的关系
    @Pointcut("execution(* com.example.demo.aspect.*Controller.*(..)) " +
            " || execution(* com.example.demo.testproperties.*Controller.*(..)) ")
    private void logPointcut() {
        // 不需要写任何实现，这里是定义一个切入点
    }
    //执行方法前可以截获，可以控制程序不进入方法
    @Before("logPointcut()")
    public void beforeRequest(JoinPoint jp) {
        log.info("请求之前（beforeRequest）");
    }
    //执行方法后可以截获，可以获取到结果，异常等
    @After("logPointcut()")
    public void afterResponse(JoinPoint jp) {
        log.info("请求之后，真正返回Response之前（afterResponse）");
    }

    //捕获异常，注意监控的Exception一定要和代码中throw出来的Exception一致
    //咱们这里用的RuntimeException
    @AfterThrowing(pointcut = "execution(* com.example.demo.aspect.*Controller.*(..)) " +
            " || execution(* com.example.demo.testproperties.*Controller.*(..)) ", throwing = "ex")
    public void logException(RuntimeException ex){
        log.info("RuntimeException:" + ex);
    }

    //捕获返回结果
    @AfterReturning(pointcut = "execution(* com.example.demo.aspect.*Controller.*(..)) " +
            " || execution(* com.example.demo.testproperties.*Controller.*(..)) ", returning = "string")
    public void logResult(String string){
        log.info("result:" + string);
    }

    //同时支持进入方法前，执行方法后，捕获异常，捕获返回结果
    @Around("logPointcut()")
    public Object retry(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("请求之前（retry）");
        try {
            Object response = proceedingJoinPoint.proceed();
            log.info("请求之后，真正返回Response之前（retry）");
            return response;
        } catch (Exception e) {
            log.info("请求之后，出现异常，设定返回null（retry）");
            return null;
        }
    }
}
