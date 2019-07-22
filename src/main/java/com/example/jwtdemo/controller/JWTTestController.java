package com.example.jwtdemo.controller;

import com.example.jwtdemo.jwt.JWTHelper;
import com.example.jwtdemo.jwt.JWTInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * jwt测试类
 * @author: clx
 * @date: 2019/7/22
 * @version: 1.1.0
 */
@RestController
@RequestMapping("/jwtTest")
public class JWTTestController {

    /**
     * 获得token
     * @param userId
     * @param userName
     * @return
     * @throws Exception
     */
    @GetMapping("/getToken")
    public Object getToken(String userId, String userName) throws Exception {
        return JWTHelper.generateToken(new JWTInfo(userId, userName), 0);
    }

    /**
     * 不需要验证的接口
     * @return
     */
    @GetMapping("/unToken")
    public String unToken() {
        return "不需要验证token,接口调用成功";
    }

    /**
     * 需要验证的接口
     * @return
     */
    @GetMapping("/needToken")
    public String needToken() {
        return "token验证正常,接口调用成功";
    }
}
