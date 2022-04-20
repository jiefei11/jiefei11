package com.dzxx.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dzxx.common.R;
import com.dzxx.entity.OrderDetail;
import com.dzxx.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 订单明细
 */
@Slf4j
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;


    @PutMapping("/list")
    public R<List<OrderDetail>> list(@RequestBody Map map){
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();


        queryWrapper.eq(OrderDetail::getOrderId,map.get("id").toString().substring(4,23));

        List<OrderDetail> list = orderDetailService.list(queryWrapper);
        return R.success(list);
    }
}