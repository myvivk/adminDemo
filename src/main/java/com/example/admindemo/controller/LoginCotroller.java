package com.example.admindemo.controller;

import com.example.admindemo.dto.LoginDTO;
import com.example.admindemo.service.AccountService;
import com.example.admindemo.service.ResourceService;
import com.example.admindemo.vo.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("auth")
public class LoginCotroller {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ResourceService resourceService;
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public String login(String username, String password, HttpSession session,
                        RedirectAttributes attributes, Model model){
        LoginDTO login = accountService.login(username, password);
        String error = login.getError();
        if(null == error){
            session.setAttribute("account", login.getAccount());
            List<ResourceVO> resourceVOS = resourceService.listResourceByRoleId(login.getAccount().getRoleId());
            model.addAttribute("resources", resourceVOS);
        }else {
            attributes.addFlashAttribute("error", error);
        }
        return login.getPath();
    }

    /**
     * 登出方法
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
