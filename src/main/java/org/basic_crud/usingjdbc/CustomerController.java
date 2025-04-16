package org.basic_crud.usingjdbc;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("api/v1/customer")
    public void insertCustomer(
            @RequestBody Customer customer
    ){
        customerService.insertCustomer(customer);
    }
}
