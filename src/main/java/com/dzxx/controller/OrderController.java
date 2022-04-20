package com.dzxx.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dzxx.common.R;
import com.dzxx.entity.Orders;
import com.dzxx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }


    @GetMapping("/page")
    public R<Page> pages(int page,int pageSize,String number){

        return orderService.pages(page,pageSize,number);
    }

    @GetMapping("/userPage")
    public R<Page> userPage(int page , int pageSize){
        return orderService.userPafe(page,pageSize);
    }

    @PutMapping
    public R<String> editOrder(@RequestBody Orders orders){
        Integer status = orders.getStatus();
        Long id = orders.getId();
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getId,id);
        Orders orders1 = orderService.getOne(queryWrapper);

        if (status == 3) {
            orders1.setStatus(3);
        } else {
            orders1.setStatus(4);
        }
        orderService.saveOrUpdate(orders1);
    return R.success("修改成功");
    }

}