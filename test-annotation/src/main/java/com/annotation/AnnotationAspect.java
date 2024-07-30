package com.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 注解的Aspect实现类
 * 这里通过AOP切面，将带有这个注解的类前后以及异常打日志
 * 另外根据注解的value值判断是否打印参数，也就是注解value值的具体用法自定义实现
 */
@Slf4j
@Aspect
@Component
public class AnnotationAspect {

    @Before(value = "@annotation(testAnnotation)")
    public void doBefore(JoinPoint joinPoint, TestAnnotation testAnnotation) {
        String value = testAnnotation.value();
        if("printParam".equals(value)) {
            log.info("执行前打印参数：{}", joinPoint.getArgs());
        } else if("dontPrintParam".equals(value)) {
            log.info("执行前不打印参数");
        }
    }

    @AfterReturning(value = "@annotation(testAnnotation)")
    public void doAfter(JoinPoint joinPoint, TestAnnotation testAnnotation) {
        String value = testAnnotation.value();
        if("printParam".equals(value)) {
            log.info("执行后打印参数：{}", joinPoint.getArgs());
        } else if("dontPrintParam".equals(value)) {
            log.info("执行后不打印参数");
        }
    }

    @AfterThrowing(value = "@annotation(testAnnotation)", throwing = "ex")
    public void doAfterThrows(JoinPoint joinPoint, Throwable ex, TestAnnotation testAnnotation) {
        String value = testAnnotation.value();
        if("printParam".equals(value)) {
            log.info("异常throw后打印参数：{}", joinPoint.getArgs());
        } else if("dontPrintParam".equals(value)) {
            log.info("异常throw后不打印参数");
        }
    }
}
