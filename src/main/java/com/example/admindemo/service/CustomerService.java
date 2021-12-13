package com.example.admindemo.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.admindemo.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author wa
 * @since 2021-12-05
 */
public interface CustomerService extends IService<Customer> {

    R<Map<String, Object>> customerList(String realName, String phone, Long page, Long limit);
}
