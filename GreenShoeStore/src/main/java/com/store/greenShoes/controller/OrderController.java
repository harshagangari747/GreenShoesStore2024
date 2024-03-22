package com.store.greenShoes.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.model.Customer;
import com.store.greenShoes.model.OrderHistory;
import com.store.greenShoes.model.Orders;
import com.store.greenShoes.repository.CustomerRepository;
import com.store.greenShoes.repository.OrderHistoryRepository;
import com.store.greenShoes.repository.OrdersRepository;
import com.store.greenShoes.service.OrderHistoryService;

@RestController
public class OrderController{
	@Autowired
	OrdersRepository orderRepo;
	@Autowired
	OrderHistoryRepository orderHistoryRepo;
	@Autowired
	OrderHistoryService orderHistoryService;
	@Autowired
	CustomerRepository customerRepo;
	@PostMapping("/order/{uid}")
	public OrderHistory postOrder(@RequestBody Orders orderData,@PathVariable("uid") Long uid) {
		System.out.println("con"+orderData);
		Customer customer=customerRepo.findByCustomerId(uid);
		orderRepo.save(orderData);
		return orderHistoryService.postorder(orderData,customer);
	}
	
	@GetMapping("/order/{uid}")
	public List<OrderHistory> getAllOrders(@PathVariable("uid") Long uid){
		Customer customer=customerRepo.findByCustomerId(uid);
		
		return orderHistoryRepo.findByCustomer(customer);
	}
	
}
