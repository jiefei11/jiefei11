package com.dzxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dzxx.entity.SetmealDish;
import com.dzxx.entity.SetmealDto;

public interface SetmealDishService extends IService<SetmealDish> {
    void saveWithDish(SetmealDto setmealDto);
}