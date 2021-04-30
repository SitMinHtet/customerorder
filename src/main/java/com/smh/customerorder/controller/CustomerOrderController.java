package com.smh.customerorder.controller;

import com.smh.customerorder.model.CustomerOrder;
import com.smh.customerorder.service.CustomerOrderService;
import lombok.Data;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@Data
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    @GetMapping("addOrder")
    public String addOrder(Model model){
        model.addAttribute("addOrder", new CustomerOrder());
        return "order/order-list";
    }

    @PostMapping("processList")
    public String processOrderList(@Valid CustomerOrder customerOrder, BindingResult result, Model model){
        if (result.hasErrors()){
            return "order/order-list";
        }
        customerOrderService.addOrder(customerOrder);
        return "redirect:/orderLists";
    }

    @GetMapping("orderLists")
    public String orderLists(Model model){
        model.addAttribute("orderLists", customerOrderService.findAll());
        return "order/order-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomerOrder(@PathVariable("id")int id){
        customerOrderService.deleteOrder(id);
        return "redirect:/orderLists";
    }

    @GetMapping("update/{id}")
    public String updateCustomerOrder(@PathVariable("id")int id,Model model){
        model.addAttribute("OrderId", customerOrderService.findById(id));
        int customerOrderId = id;
        return "order/update-form";
    }

    int customerOrderId;

    @PostMapping("/update")
    public String processUpdate(@Valid CustomerOrder customerOrder){
        customerOrderService.updateOrder(customerOrderId,customerOrder);
        return "redirect:/orderLists";
    }

    @GetMapping("/search")
    public String search(Model model, @Param("keyword") String keyword, HttpServletRequest request) {
        //List<CustomerOrder> customerOrders = customerOrderService.search(keyword);
        //model.addAttribute("orders", customerOrders);
        //model.addAttribute("keyword", keyword);
        CustomerOrder customerOrder = (CustomerOrder) customerOrderService.search(request.getParameter("Order ID"));
        return "redirect:/orderLists";
    }


    @ModelAttribute("statusList")
    public List<String> statusList(){
        return Arrays.asList(
                "Shipped",
                "Ordered",
                "Order Cancellation"

        );
    }

    //Order Entry
    @GetMapping("addEntry")
    public String addEntry(Model model){
        model.addAttribute("addEntry", new CustomerOrder());
        return "order/order-entry";
    }

    @PostMapping("processEntry")
    public String processEntryList(@Valid CustomerOrder customerOrder, BindingResult result){
        if (result.hasErrors()){
            return "order/order-entry";
        }
        customerOrderService.addOrder(customerOrder);
        return "redirect:/entries";
    }

    @GetMapping("entries")
    public String entryLists(Model model){
        model.addAttribute("entryLists", customerOrderService.findAll());
        return "order/test";
    }


    @ModelAttribute("productType")
    public List<String> productTypes(){
        return Arrays.asList(
                "PT00004|Supplements",
                "PT00001|Painkiller",
                "PT00002|Throatloze",
                "PT00003|Antiseptics"

        );
    }


    @ModelAttribute("productName")
    public List<String> products(){
        return Arrays.asList(
                "P00004|Lensen",
                "P00016|Poy-sian",
                "P00008|Hirudoid",
                "P00004|Lensenn"

        );
    }
}
