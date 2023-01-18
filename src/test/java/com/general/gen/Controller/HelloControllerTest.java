package com.general.gen.Controller;

import com.general.gen.config.auth.SecurityConfig;
import com.general.gen.web.HelloController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


/**
 * @WebMvcTest 는 WebSecurityConfigurerAdapter, WebMvcConfigurer를 비롯한
 * @ControllerAdvice, @Controller를 읽는다.
 * 즉, @Repository, @Service, @Component는 스캔 대상이 아니다.
 * 그러니 SecurityConfig는 읽었지만, SecurityConfig를 생성하기 위해 필요한 CustomOAuth2UserService는 읽을수가 없어 에러 발생.
 * 이 문제를 해결하기 위해 스캔 대상에서 SecurityConfig를 제거
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
//@WithMockUser   // spring-security를 사용하기 때문에 test시 자동 인증이 가능하도록 한다.
class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("hello가 리턴된다.")
    @WithMockUser(roles = "USER")
    public void hello() throws Exception{
        String hello = "hello";

        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(hello));
    }

    @Test
    @DisplayName("helloDTO가 리턴된다")
    @WithMockUser(roles = "USER")
    public void helloDTO() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(MockMvcRequestBuilders
                .get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.amount").value(amount));

        //when

        //then
    }


}