package com.miaoph.cn.networknetty.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String age;
    private Date date;
}
