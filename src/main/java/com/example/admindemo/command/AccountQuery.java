package com.example.admindemo.command;

import lombok.Data;

@Data
public class AccountQuery {

    private String realName;
    private String email;
    private String createTimeRange;

    private Long page;

    private Long limit;
}
