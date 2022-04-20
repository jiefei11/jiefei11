package com.dzxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzxx.common.CustomException;
import com.dzxx.common.R;
import com.dzxx.entity.Category;
import com.dzxx.entity.Dish;
import com.dzxx.entity.Setmeal;
import com.dzxx.mapper.CategoryMapper;
import com.dzxx.service.CategoryService;
import com.dzxx.service.DishService;
import com.dzxx.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;


    @Override
    public R<Page> pages(int page, int pageSize) {
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Category::getSort);
        page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @Override
    public R<String> insert(Category category) {
        save(category);
        return R.success("保存成功");
    }

    @Override
    public void delete(Long id) {
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
        dishQueryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(dishQueryWrapper);
        if (count>0){
            throw new CustomException("当前分类下关联了菜品，无法删除");
        }
        LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper<>();
        setmealQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(setmealQueryWrapper);
        if (count1>0){
            throw new CustomException("当前套餐下关联了菜品，无法删除");
        }

        removeById(id);
    }


}
