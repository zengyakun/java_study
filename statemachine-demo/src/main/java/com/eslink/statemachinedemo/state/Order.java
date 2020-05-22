package com.eslink.statemachinedemo.state;

import lombok.Data;

/**
 *@ClassName Order
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2020/5/21 16:47
 *@Version 1.0
 **/
@Data
public class Order {

    private OrderStatus status;

    private int id;
}
