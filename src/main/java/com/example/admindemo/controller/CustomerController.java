package com.example.admindemo.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admindemo.entity.Customer;
import com.example.admindemo.service.CustomerService;
import com.example.admindemo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author wa
 * @since 2021-12-05
 */
@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("toList")
    public String toList(){
        return "customer/customerList";
    }

    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(String realName, String phone, Long page, Long limit){
        return customerService.customerList(realName,phone,page,limit);
    }

    /**
     * 进入新增页
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd(){
        return "customer/customerAdd";
    }

    @PostMapping("add")
    @ResponseBody
    public R<Object> add(@RequestBody Customer customer){
        return ResultUtil.buildR(customerService.save(customer));
    }

    /**
     * 进入修改页
     * @param customerId
     * @param model
     * @return
     */
    @GetMapping("toUpdate/{customerId}")
    public String toUpdate(@PathVariable Long customerId, Model model){
        Customer customer = customerService.getById(customerId);
        model.addAttribute("customer", customer);
        return "customer/customerUpdate";
    }

    @PutMapping("update")
    @ResponseBody
    public R<Object> update(@RequestBody Customer customer){
        return ResultUtil.buildR(customerService.updateById(customer));
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable String id){
        return ResultUtil.buildR(customerService.removeById(id));
    }

    @GetMapping("toDetail/{customerId}")
    public String toDetail(@PathVariable Long customerId, Model model){
        Customer customer = customerService.getById(customerId);
        model.addAttribute("customer", customer);
        return "customer/customerDetail";
    }
}
