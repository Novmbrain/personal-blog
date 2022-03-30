package com.wenjie.blog.web.admin;

import com.wenjie.blog.pojo.Type;
import com.wenjie.blog.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 分页查询处理，每一页最多有 10 条
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
            , Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "/admin/types";
    }
    /*
    * 功能：新增按钮
    * 如果是新增一个 Type，那么就从在 model 中加一个空的 type 来
    * */
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "/admin/types-input";
    }

    /*
    * 功能：修改按钮
    * 如果是修改一个 Type，那么就将根据 id 查询到需要修改的 type，然后加入 model 中返回
    * */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "/admin/types-input";
    }
    /*
    * 功能：删除按钮
    * */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
    /*当新增 type 时调用
    * BindingResult 和 type 一定要挨着
    * */
    @PostMapping("/types")
    public String post(@Validated Type type, BindingResult result, RedirectAttributes redirectAttributes) {
        // 进行后端 type name 重复验证
        if (typeService.getTypeByName(type.getName()) != null) {
            result.rejectValue("name", "nameError", "不能添加重复的分类");
        }

        // 进行后端 name 非空校验
        if (result.hasErrors()) {
            return "admin/types-input";
        }

        Type t = typeService.saveType(type);
        if (t == null) {
            redirectAttributes.addFlashAttribute("message", "新增失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    /*当修改 type 时调用*/
    @PostMapping("/types/{id}")
    public String editPost(Type type, BindingResult result, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        // 进行后端 type name 重复验证
        if (typeService.getTypeByName(type.getName()) != null) {
            result.rejectValue("name", "nameError", "不能添加重复的分类");
        }

        // 进行后端 name 非空校验
        if (result.hasErrors()) {
            return "admin/types-input";
        }

        Type t = typeService.updateType(id, type);
        if (t == null) {
            redirectAttributes.addFlashAttribute("message", "更新失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }
}
