package com.zyan.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-17 下午9:24
 */

@Controller
@Slf4j
public class TestController {


    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "test";
    }
}
