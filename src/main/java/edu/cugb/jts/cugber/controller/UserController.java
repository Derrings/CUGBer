package edu.cugb.jts.cugber.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    @RequestMapping("/login")
    public String login() {
        return "赵帅你好帅";
    }
}
