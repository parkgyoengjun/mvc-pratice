package org.example.mvc.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // 클래스에 애노테이션 붙일수잇음
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
