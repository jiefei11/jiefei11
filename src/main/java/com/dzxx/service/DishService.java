package com.dzxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dzxx.common.R;
import com.dzxx.entity.Dish;
import com.dzxx.entity.DishDto;

public interface DishService extends IService<Dish> {
    R<Page> pages(int page, int pageSize, String name);

    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);
}