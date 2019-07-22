package com.example.jwtdemo.jwt;

import lombok.Data;

import java.io.Serializable;

/**
 * JWT中保存的信息
 * @author: clx
 * @date: 2019/7/22
 * @version: 1.1.0
 */
@Data
public class JWTInfo implements Serializable {
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户名字
     */
    private String userName;

    public JWTInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
