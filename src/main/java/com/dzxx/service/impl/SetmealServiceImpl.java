package com.dzxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzxx.common.CustomException;
import com.dzxx.entity.Setmeal;
import com.dzxx.entity.SetmealDish;
import com.dzxx.entity.SetmealDto;
import com.dzxx.mapper.SetmealMapper;
import com.dzxx.service.SetmealDishService;
import com.dzxx.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids)
                    .eq(Setmeal::getStatus,1);

        int count = count(queryWrapper);
        if (count>0){
            throw new CustomException("套餐正在售卖，不能删除");
        }
        removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> setmealDishQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishQueryWrapper.in(SetmealDish::getSetmealId,ids);

        setmealDishService.remove(setmealDishQueryWrapper);
    }
}