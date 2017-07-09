package com.spring.cache.model;

import lombok.Data;

/**
 * Created by zhangyubo02 on 2017/7/6.
 */
@Data
public class Account {
    private int id;
    private String name;
    private String password;

    public Account(String name) {
        this.name = name;
    }

}
