package com.wenjie.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: BlogController
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 25/03/2022
 **/
@Controller
@RequestMapping("/admin")
public class BlogController {
    @GetMapping("/blogs")
    public String blogs() {
        return "/admin/blogs";
    }
}
