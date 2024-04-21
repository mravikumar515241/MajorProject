package com.finalproject.hotelbookingsystem.service;

import com.finalproject.hotelbookingsystem.dto.CustomerDto;
import com.finalproject.hotelbookingsystem.dto.CustomerUpdateDto;
import com.finalproject.hotelbookingsystem.entity.CustomerEntity;
import com.finalproject.hotelbookingsystem.exceptions.CustomerIdDoesNotExistException;
import com.finalproject.hotelbookingsystem.repository.CustomerRepository;
import com.finalproject.hotelbookingsystem.service.interfaces.CustomerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        logger.info("Customer Service called");
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<CustomerDto> getAllCustomers() {
        logger.info("getALlCustomers called");
        return customerRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, CustomerDto.class))
                .toList();
    }
    @Override
    public CustomerDto getCustomerById(Long id) {
        logger.info("getCustomerById called");
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        if(customerEntity.isEmpty()){
            throw new CustomerIdDoesNotExistException("Customer ID not found");
        }
        logger.info("getALlCustomers exiting");
        return modelMapper.map(customerEntity.get(), CustomerDto.class);
    }
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        logger.info("createCustomer called");
        CustomerEntity customerEntity = customerRepository.save(modelMapper.map(customerDto, CustomerEntity.class));
        logger.info("createCustomer exiting");
        return modelMapper.map(customerEntity, CustomerDto.class);
    }
    @Override
    public CustomerDto updateCustomer(Long id, CustomerUpdateDto customerUpdateDto) {
        logger.info("updateCustomer called");
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(id);
        if(optionalCustomerEntity.isEmpty()){
            throw new CustomerIdDoesNotExistException("Customer ID not found");
        }
        CustomerEntity customerEntity = optionalCustomerEntity.get();
        modelMapper.map(customerUpdateDto, customerEntity);
        customerRepository.save(customerEntity);
        logger.info("updateCustomer exiting");
        return modelMapper.map(optionalCustomerEntity.get(), CustomerDto.class);
    }
    @Override
    public void deleteCustomer(Long id) {
        logger.info("deleteCustomer called");
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            logger.info("Customer deleted successfully");
        }
        else{
            logger.error("Customer Id to be deleted does not exist");
        }
        logger.info("deleteCustomer exiting");
    }
}
