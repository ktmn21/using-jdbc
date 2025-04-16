package org.basic_crud.usingjdbc;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void insertCustomer(Customer customer){
        customerDAO.insertCustomer(customer);
    }
}
