package com.smh.customerorder.repository;

import com.smh.customerorder.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Integer> {


    @Query("select c from CustomerOrder c where concat(c.orderedId,c.orderedDate,c.status) LIKE %?1% ")
     List<CustomerOrder>  search(String keyword);
}
