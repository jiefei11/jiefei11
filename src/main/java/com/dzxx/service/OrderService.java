package com.dzxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dzxx.common.R;
import com.dzxx.entity.Orders;

public interface OrderService extends IService<Orders> {
    void submit(Orders orders);

    R<Page> pages(int page, int pageSize, String number);

    R<Page> userPafe(int page, int pageSize);
}