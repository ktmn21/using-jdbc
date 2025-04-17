package org.basic_crud.usingjdbc;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void insertCustomer(
            @RequestBody Customer customer
    ){
        customerService.insertCustomer(customer);
    }

    @GetMapping
    public List<Customer> selectAllCustomers(){
        return customerService.selectAllCustomers();
    }

    @GetMapping("{id}")
    public Customer selectCustomerById(
            @PathVariable("id")
            Integer id
    ){
        return customerService.selectCustomerById(id);
    }

    @DeleteMapping({"{id}"})
    public void deleteCustomer(
            @PathVariable("id")
            Integer id){
        customerService.deleteCustomer(id);
    }

    @PostMapping({"{id}"})
    public void updateCustomer(
            @PathVariable("id") Integer id,
            @RequestBody CustomerUpdateRequest customerUpdateRequest
    ){
        customerService.updateCustomer(id, customerUpdateRequest);
    }

}
