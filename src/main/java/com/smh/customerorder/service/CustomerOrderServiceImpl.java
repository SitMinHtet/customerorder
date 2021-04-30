package com.smh.customerorder.service;

import com.smh.customerorder.model.CustomerOrder;
import com.smh.customerorder.repository.CustomerOrderRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Data
public class CustomerOrderServiceImpl implements CustomerOrderService{

    private final CustomerOrderRepo customerOrderRepo;

    @Override
    public CustomerOrder addOrder(CustomerOrder customerOrder) {
        return customerOrderRepo.save(customerOrder);
    }

    @Override
    public List<CustomerOrder> findAll() {
        return customerOrderRepo.findAll();
    }

    @Override
    public CustomerOrder findById(int id) {
        return customerOrderRepo.findById(id).orElseThrow();
    }

    @Override
    public void deleteOrder(int id) {
        customerOrderRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void updateOrder(int id, CustomerOrder customerOrder) {
        CustomerOrder oldCustomerOrder = findById(id);
        oldCustomerOrder.setOrderedId(customerOrder.getOrderedId());
        oldCustomerOrder.setDesiredDeliveryDate(customerOrder.getDesiredDeliveryDate());
        oldCustomerOrder.setOrderedDate(customerOrder.getOrderedDate());
        oldCustomerOrder.setStatus(customerOrder.getStatus());
    }

    @Override
    public List<CustomerOrder> search(String keyword) {
        if(keyword != null){
            return customerOrderRepo.search(keyword);
        }
        return customerOrderRepo.findAll();
    }
}
