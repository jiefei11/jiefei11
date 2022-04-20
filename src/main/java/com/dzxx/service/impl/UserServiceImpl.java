package com.dzxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzxx.entity.User;
import com.dzxx.mapper.UserMapper;
import com.dzxx.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}