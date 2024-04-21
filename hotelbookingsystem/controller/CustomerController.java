package com.finalproject.hotelbookingsystem.controller;

import com.finalproject.hotelbookingsystem.dto.CustomerDto;
import com.finalproject.hotelbookingsystem.dto.CustomerUpdateDto;
import com.finalproject.hotelbookingsystem.service.interfaces.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-api/v1")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    @GetMapping("/customers/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") Long customerId) {
        return customerService.getCustomerById(customerId);
    }
    @PostMapping("/customers")
    public CustomerDto createCustomer(@RequestBody @Valid CustomerDto customer) {
        return customerService.createCustomer(customer);
    }
    @PutMapping("/customers/{id}")
    public CustomerDto updateCustomer(@PathVariable("id") Long customerId, @RequestBody @Valid CustomerUpdateDto customer) {
        return customerService.updateCustomer(customerId, customer);
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable("id") Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}
