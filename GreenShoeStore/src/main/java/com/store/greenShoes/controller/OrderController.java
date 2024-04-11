package com.store.greenShoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.DTO.OrderDTO;
import com.store.greenShoes.model.Users;
import com.store.greenShoes.repository.OrdersRepository;
import com.store.greenShoes.repository.UsersRepository;
import com.store.greenShoes.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	OrdersRepository orderRepo;
	@Autowired
	OrderService orderService;
	@Autowired
	UsersRepository userRepo;

	@PostMapping("/order")
	public OrderDTO postOrder(@RequestBody OrderDTO orderData) {
		System.out.println("con" + orderData);
		Users customer = null;
		if (orderData.getUserId() != null) {
			customer = userRepo.getReferenceById(orderData.getUserId());
		}
		return orderService.postorder(orderData, customer);
	}

	@GetMapping("/order/{uid}")
	public List<OrderDTO> getAllOrders(@PathVariable("uid") Long uid) {
		return orderService.getAllOrders(uid);

	}

	@GetMapping("/order/{oid}")
	public ResponseEntity<OrderDTO> getOrder(@PathVariable("oid") Long oid) {
		try {
			OrderDTO getOrder = orderService.getOrder(oid);
			return ResponseEntity.ok(getOrder);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(null);
		}

	}

}
