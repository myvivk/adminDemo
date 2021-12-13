package com.example.admindemo.dto;

import com.example.admindemo.entity.Account;
import lombok.Data;

@Data
public class LoginDTO {
    /**
     * 重定向或跳转的路径
     */
    private String path;
    /**
     * 错误提示信息
     */
    private String error;
    /**
     * 登录信息
     */
    private Account account;
}
