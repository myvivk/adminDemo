package com.example.admindemo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admindemo.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admindemo.entity.Resource;
import com.example.admindemo.vo.ResourceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author wa
 * @since 2021-12-05
 */
public interface AccountMapper extends BaseMapper<Account> {

    IPage<Account> accountPage(Page<Account> page, @Param(Constants.WRAPPER) Wrapper<Account> wrapper);

    Account queryAccountById( @Param("accountId") Long accountId);
}
