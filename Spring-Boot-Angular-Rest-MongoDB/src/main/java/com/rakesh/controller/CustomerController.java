package com.rakesh.controller;

/**
 * 
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.DAO.CustomerRepository;
import com.rakesh.model.Customer;


/**
 * @author Rakesh.kakarla
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
 
 @Autowired
 CustomerRepository customerRepository;
 /**
  * GET /create  --> Create a new Customer and save it in the database.
  */
 @RequestMapping("/create")
 public Map<String, Object> create(Customer customer) {
	 System.out.println("Inside create");
	 customer.setFirstName("Rakesh");
	 customer.setLastName("kakarla");
	 customer = customerRepository.save(customer);
  Map<String, Object> dataMap = new HashMap<String, Object>();
  dataMap.put("message", "Customer created successfully");
  dataMap.put("status", "1");
  dataMap.put("customer", customer);
     return dataMap;
 }
 
 /**
  * GET /read  --> Read a Customer by Customer id from the database.
  */
 @RequestMapping("/read")
 public Map<String, Object> read(@RequestParam String customerId) {
	 Customer customer = customerRepository.findOne(customerId);
  Map<String, Object> dataMap = new HashMap<String, Object>();
  dataMap.put("message", "Customer found successfully");
  dataMap.put("status", "1");
  dataMap.put("customer", customer);
     return dataMap;
 }
 
 /**
  * GET /update  --> Update a Customer record and save it in the database.
  */
 @RequestMapping("/update")
 public Map<String, Object> update(@RequestParam String customerId, @RequestParam String firstName) {
	 Customer customer = customerRepository.findOne(customerId);
	 customer.setFirstName(firstName);
	 customer = customerRepository.save(customer);
  Map<String, Object> dataMap = new HashMap<String, Object>();
  dataMap.put("message", "Customer updated successfully");
  dataMap.put("status", "1");
  dataMap.put("customer", customer);
     return dataMap;
 }
 
 /**
  * GET /delete  --> Delete a Customer from the database.
  */
 @RequestMapping("/delete")
 public Map<String, Object> delete(@RequestParam String customerId) {
	 customerRepository.delete(customerId);
  Map<String, Object> dataMap = new HashMap<String, Object>();
  dataMap.put("message", "Customer deleted successfully");
  dataMap.put("status", "1");
     return dataMap;
 }
 
 /**
  * GET /read  --> Read all Customer from the database.
  */
 @RequestMapping("/read-all")
 public Map<String, Object> readAll() {
  List<Customer> customers = customerRepository.findAll();
  Map<String, Object> dataMap = new HashMap<String, Object>();
  dataMap.put("message", "Customer found successfully");
  dataMap.put("totalCustomers", customers.size());
  dataMap.put("status", "1");
  dataMap.put("customers", customers);
     return dataMap;
 }
}
