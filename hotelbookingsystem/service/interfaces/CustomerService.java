package com.finalproject.hotelbookingsystem.service.interfaces;

import com.finalproject.hotelbookingsystem.dto.CustomerDto;
import com.finalproject.hotelbookingsystem.dto.CustomerUpdateDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(Long id);
    CustomerDto createCustomer(CustomerDto customer);
    CustomerDto updateCustomer(Long id, CustomerUpdateDto customer);
    void deleteCustomer(Long id);
}
