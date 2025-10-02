package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;
    @Scheduled(cron="0 * * * * ?")
    public void processTimeOutOrder(){
        log.info("定时处理超时订单:{}", LocalDateTime.now());
        List<Orders> ordersList = orderMapper.getByStatusAndTimeLT(Orders.PENDING_PAYMENT, LocalDateTime.now().minusMinutes(15));
        if(ordersList!=null&&ordersList.size()>0){
            for(Orders orders:ordersList){
                // 取消订单逻辑
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("超时未支付");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }

    @Scheduled(cron="0 0 1 * * ?")
    public void processDeliveryOrder(){
        log.info("定时处理配送中的订单:{}", LocalDateTime.now());
        List<Orders> ordersList = orderMapper.getByStatusAndTimeLT(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().minusMinutes(60));
        if(ordersList!=null&&ordersList.size()>0){
            for(Orders orders:ordersList){
                // 取消订单逻辑
                orders.setStatus(Orders.COMPLETED);
                orderMapper.update(orders);
            }
        }
    }
}
