package com.wenjie.blog.web.admin;

import com.wenjie.blog.pojo.Tag;
import com.wenjie.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @className: TypeController
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 26/03/2022
 **/
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("tags")
    public String tags(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "/admin/tags";
    }

    /*
    * 功能：新增按钮
    * 如果是新增一个 Tag，那么就从在 model 中加一个空的 tag 来填充页面
    * */
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "/admin/tags-input";
    }


    /*
    * 功能：修改按钮
    * 如果是修改一个 Tag，那么就将根据 id 查询到需要修改的 tag，然后加入 model 中返回
    * */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "/admin/tags-input";
    }
    /*
    * 功能：删除按钮
    * */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message", "Delete successful");
        return "redirect:/admin/tags";
    }


    /*当新增 tag 时调用
    * BindingResult 和 tag 一定要挨着
    * */
    @PostMapping("/tags")
    public String post(@Validated Tag tag, BindingResult result, RedirectAttributes redirectAttributes) {
        // 进行后端 tag name 重复验证
        if (tagService.getTagByName(tag.getName()) != null) {
            result.rejectValue("name", "nameError", "No duplicate categories can be added");
        }

        // 进行后端 name 非空校验
        if (result.hasErrors()) {
            return "admin/tags-input";
        }

        Tag t = tagService.saveTag(tag);
        if (t == null) {
            redirectAttributes.addFlashAttribute("message", "Add Failed");
        } else {
            redirectAttributes.addFlashAttribute("message", "Add Successful");
        }
        return "redirect:/admin/tags";
    }

    /*当修改 tag 时调用*/
    @PostMapping("/tags/{id}")
    public String editPost(Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        // 进行后端 tag name 重复验证
        if (tagService.getTagByName(tag.getName()) != null) {
            result.rejectValue("name", "nameError", "No duplicate categories can be added");
        }

        // 进行后端 name 非空校验
        if (result.hasErrors()) {
            return "admin/tags-input";
        }

        Tag t = tagService.updateTag(id, tag);
        if (t == null) {
            redirectAttributes.addFlashAttribute("message", "Update Failed");
        } else {
            redirectAttributes.addFlashAttribute("message", "Update Successful");
        }
        return "redirect:/admin/tags";
    }

}
