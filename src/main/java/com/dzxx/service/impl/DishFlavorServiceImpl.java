package com.dzxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzxx.entity.DishFlavor;
import com.dzxx.mapper.DishFlavorMapper;
import com.dzxx.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}