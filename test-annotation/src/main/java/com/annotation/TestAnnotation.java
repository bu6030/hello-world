package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target表示在类的哪个部分使用的注解
 * METHOD表示在方法使用的注解，其他还有方法使用的
 * 属性值使用的，参数使用，构造器使用的
 */
@Target(ElementType.METHOD)
/**
 * @Retention表示何时生效
 * RUNTIME表示运行时使用的注解，在源代码中保留，编译后保留，运行时仍然可以使用
 * SOURCE表示源代码级别使用的注解，仅在源代码中保留，编译后消失
 * CLASS表示类级别使用的注解，在源代码中保留，编译后保留，但是运行时无法使用
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    // 定义注解的属性，我们这里将其设定为打印参数以及不打印参数的两种类型
    String value();
}
