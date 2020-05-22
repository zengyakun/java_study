package com.eslink.statemachinedemo.state;

import java.util.Map;

/**
 *@ClassName OrderService
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/21 17:03
 *@Version 1.0
 **/
public interface OrderService {
    Order creat();

    /*付款*/
    Order pay(int id);

    /*发货*/
    Order deliver(int id);

    /*收货*/
    Order receive(int id);

    Map<Integer, Order> getOrders();
}
