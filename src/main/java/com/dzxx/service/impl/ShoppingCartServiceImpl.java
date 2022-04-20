package com.dzxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzxx.entity.ShoppingCart;
import com.dzxx.mapper.ShoppingCartMapper;
import com.dzxx.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}