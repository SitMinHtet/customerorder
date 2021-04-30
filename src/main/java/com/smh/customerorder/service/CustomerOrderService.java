package com.smh.customerorder.service;

import com.smh.customerorder.model.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {

    CustomerOrder addOrder(CustomerOrder customerOrder);
    List<CustomerOrder> findAll();
    CustomerOrder findById(int id);
    void deleteOrder(int id);
    void updateOrder(int id,CustomerOrder customerOrder);
    List<CustomerOrder> search(String keyword);
}
