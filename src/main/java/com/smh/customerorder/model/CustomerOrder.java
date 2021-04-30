package com.smh.customerorder.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String orderedId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderedDate;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate DesiredDeliveryDate;
    private String phoneNumber;
    @Column(columnDefinition = "TEXT")
    private String Address;
    private String productType;
    private String productName;
    private int noOfOrdered;

}
