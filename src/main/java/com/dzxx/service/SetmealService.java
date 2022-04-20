package com.dzxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dzxx.entity.Setmeal;
import com.dzxx.entity.SetmealDto;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    void removeWithDish(List<Long> ids);
}