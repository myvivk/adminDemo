package com.example.admindemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admindemo.entity.Customer;
import com.example.admindemo.mapper.CustomerMapper;
import com.example.admindemo.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admindemo.util.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author wa
 * @since 2021-12-05
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Override
    public R<Map<String, Object>> customerList(String realName, String phone, Long page, Long limit) {

        Page<Customer> list = lambdaQuery().like(StringUtils.isNotBlank(realName), Customer::getRealName, realName)
                .like(StringUtils.isNotBlank(phone), Customer::getPhone, phone)
                .page(new Page<Customer>(page, limit));
        return ResultUtil.buildPageR(list);
    }
}
