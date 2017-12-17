package com.neil.oms.service;

import com.neil.oms.model.*;

/**
 * Created by neilmendum on 16/12/2017.
 */
public interface OrderService {

    OrderStatus placeOrder(OrderInput orderInput);

    long getNextId();

}