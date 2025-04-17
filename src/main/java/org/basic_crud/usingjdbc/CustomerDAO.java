package org.basic_crud.usingjdbc;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    void insertCustomer(Customer customer);
    void deleteCustomerById(Integer id);
    void updateCustomer(Customer update);

    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Integer id);

    boolean existsPersonWithEmail(String email);
    boolean existsPersonWithId(Integer id);


}
