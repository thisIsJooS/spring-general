package com.general.gen.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1.
 * 이 어노테이션이 생성될 수 있는 위치를 지정한다.
 * PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있다.
 * 이 외에도 클래스 선언문에 쓸 수 있는 TYPE 등이 있다.
 */

/**
 * 2.
 * @Retention 어노테이션은 라이프사이클,
 * 즉, 어노테이션이 언제까지 살아 남아 있을지를 정하는 역할을 한다.
 */

@Target(ElementType.PARAMETER) // 1
@Retention(RetentionPolicy.RUNTIME) // 2
public @interface LoginUser {
}



