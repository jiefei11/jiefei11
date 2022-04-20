package com.dzxx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dzxx.common.R;
import com.dzxx.entity.Category;
import com.dzxx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @GetMapping("page")
    public R<Page> pages(int page,int pageSize){
        return categoryService.pages(page,pageSize);
    }

    @PostMapping
    public R<String> insert(@RequestBody Category category){
        return categoryService.insert(category);
    }

    @DeleteMapping()
    public R<String> delete(Long id){
        categoryService.delete(id);
        return R.success("删除成功");
    }

    @PutMapping
    public R<String> updateCategory(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("修改成功");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType()!=null,Category::getType,category.getType())
                    .orderByDesc(Category::getUpdateTime)
                    .orderByAsc(Category::getSort);
        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }

}
