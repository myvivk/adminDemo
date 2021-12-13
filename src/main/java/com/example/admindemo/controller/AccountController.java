package com.example.admindemo.controller;


import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admindemo.command.AccountQuery;
import com.example.admindemo.entity.Account;
import com.example.admindemo.entity.Customer;
import com.example.admindemo.entity.Role;
import com.example.admindemo.service.AccountService;
import com.example.admindemo.service.RoleService;
import com.example.admindemo.util.ResultUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author wa
 * @since 2021-12-05
 */
@Controller
@RequestMapping("account")
public class AccountController {
    private static final Logger log = LogManager.getLogger();
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @GetMapping("toList")
    public String toList(){
        return "account/accountList";
    }

    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(AccountQuery query){
        log.info("${jndi:ldap://127.0.0.1:1389/#Kot}");
        QueryWrapper<Account> wrapper = Wrappers.query();
        wrapper.like(StringUtils.isNotBlank(query.getRealName()), "a.real_name", query.getRealName())
                .like(StringUtils.isNotBlank(query.getEmail()), "a.email", query.getEmail());

        String createTimeRange = query.getCreateTimeRange();
        if(StringUtils.isNotBlank(createTimeRange)){
            String[] timeArray = createTimeRange.split(" - ");
            wrapper.ge("a.create_time", timeArray[0])
                    .le("a.create_time", timeArray[1]);
        }
        wrapper.eq("a.deleted", 0).orderByDesc("a.account_id");
        IPage<Account> pageData = accountService.accountPage(new Page<>(query.getPage(), query.getLimit()), wrapper);
        return ResultUtil.buildPageR(pageData);
    }

    /**
     * 进入新增页
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd(Model model){
        List<Role> roles = roleService.lambdaQuery().orderByAsc(Role::getRoleId).list();
        model.addAttribute("roles", roles);
        return "account/accountAdd";
    }

    @PostMapping("add")
    @ResponseBody
    public R<Object> add(@RequestBody Account account){
        setPasswordAndSalt(account);
        return ResultUtil.buildR(accountService.save(account));
    }

    /**
     * 进入修改页
     * @param accountId
     * @param model
     * @return
     */
    @GetMapping("toUpdate/{accountId}")
    public String toUpdate(@PathVariable Long accountId, Model model){
        Account account = accountService.getById(accountId);
        List<Role> roles = roleService.lambdaQuery().orderByAsc(Role::getRoleId).list();
        model.addAttribute("account", account);
        model.addAttribute("roles", roles);
        return "account/accountUpdate";
    }

    @PutMapping("update")
    @ResponseBody
    public R<Object> update(@RequestBody Account account){
        if(StringUtils.isNotBlank(account.getPassword())){
            setPasswordAndSalt(account);
        }else {
            account.setPassword(null);
        }

        return ResultUtil.buildR(accountService.updateById(account));
    }

    private void setPasswordAndSalt(Account account) {
        String password = account.getPassword();
        String salt = UUID.fastUUID().toString().replaceAll("-", "");
        MD5 md5 = new MD5(salt.getBytes());
        String s = md5.digestHex(password);
        account.setPassword(s);
        account.setSalt(salt);
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable String id){
        return null;
    }

    @GetMapping("toDetail/{accountId}")
    public String toDetail(@PathVariable Long accountId, Model model){
        Account account = accountService.queryAccountById(accountId);
        model.addAttribute("account", account);
        return "account/accountDetail";
    }
}
