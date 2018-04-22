package pers.yangs.winterframework.web.annotation;

import jdk.nashorn.internal.ir.annotations.Reference;
import jdk.nashorn.internal.runtime.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Ytadp on 2018/4/22.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    String value() default "";
}
