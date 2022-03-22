package com.wenjie.blog.web.admin;

import com.wenjie.blog.pojo.User;
import com.wenjie.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @className: LoginController
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 21/03/2022
 **/
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "/admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password,
                        HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.checkUser(userName, password);

        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "/admin/index";
        } else {
            redirectAttributes.addFlashAttribute("messsga", "用户名和密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
