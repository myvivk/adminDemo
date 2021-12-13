package com.example.admindemo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admindemo.dto.LoginDTO;
import com.example.admindemo.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author wa
 * @since 2021-12-05
 */
public interface AccountService extends IService<Account> {

    LoginDTO login(String username, String password);

    IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper);

    Account queryAccountById(Long accountId);
}
