package com.dzxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzxx.entity.SetmealDish;
import com.dzxx.entity.SetmealDto;
import com.dzxx.mapper.SetmealDishMapper;
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
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {

    @Autowired
    private SetmealService setmealService;

    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        setmealService.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        saveBatch(setmealDishes);
    }
}