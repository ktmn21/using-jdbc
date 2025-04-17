package org.basic_crud.usingjdbc;

import org.basic_crud.usingjdbc.exceptions.DuplicateResourceException;
import org.basic_crud.usingjdbc.exceptions.RequestValidationException;
import org.basic_crud.usingjdbc.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void insertCustomer(Customer customer){
        if(customerDAO.existsPersonWithEmail(customer.getEmail())){
            throw new DuplicateResourceException("The email is not unique");
        }
        customerDAO.insertCustomer(customer);
    }

    public List<Customer> selectAllCustomers(){
        return customerDAO.selectAllCustomers();
    }

    public Customer selectCustomerById(Integer id){
        return customerDAO.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The customer with id [%d] not found".formatted(id)
                ));
    }

    public void deleteCustomer(Integer id){
        if(!customerDAO.existsPersonWithId(id)){
            throw new ResourceNotFoundException(
                    "Customer with id [%d] not found".formatted(id)
            );
        }
        customerDAO.deleteCustomerById(id);
    }

    public void updateCustomer(Integer id, CustomerUpdateRequest customerUpdateRequest){
        Customer target = selectCustomerById(id);
        boolean changed = false;

        if(customerUpdateRequest.name() != null && !customerUpdateRequest.name().equals(target.getName())){
            target.setName(customerUpdateRequest.name());
            changed = true;
        }

        if(customerUpdateRequest.email() != null && !customerUpdateRequest.email().equals(target.getEmail())){
            String newEmail = customerUpdateRequest.email();
            if(customerDAO.existsPersonWithEmail(newEmail)){
                throw new DuplicateResourceException(
                        "The email is not unique"
                );
            }
            target.setEmail(newEmail);
            changed = true;
        }

        if(customerUpdateRequest.age() != null && !customerUpdateRequest.age().equals(target.getAge())){
            target.setAge(customerUpdateRequest.age());
            changed = true;
        }

        if(!changed){
            throw new RequestValidationException(
                    "No data changes were found."
            );
        }

        customerDAO.updateCustomer(target);
    }
}
