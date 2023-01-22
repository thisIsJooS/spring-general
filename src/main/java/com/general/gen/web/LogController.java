package com.general.gen.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class LogController {
    @GetMapping("/log")
    public String log(HttpServletRequest request){
        return "log";
    }
}
