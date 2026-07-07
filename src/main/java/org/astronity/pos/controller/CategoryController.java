package org.astronity.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping
    public String listCategories(Model model)
    {
        return "pos/categories/index";
    }

    // showCreateForm
    // creteCategory
    // showEditForm
    // updateCategory
    // deleteCategory
}
