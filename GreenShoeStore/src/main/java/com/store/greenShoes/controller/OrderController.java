package com.store.greenShoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.DTO.OrderDTO;
import com.store.greenShoes.DTO.ResponseOrderDTO;
import com.store.greenShoes.model.Users;
import com.store.greenShoes.repository.OrdersRepository;
import com.store.greenShoes.repository.UsersRepository;
import com.store.greenShoes.service.OrderService;

@RestController
@CrossOrigin(origins="http://localhost:3000",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class OrderController {
	@Autowired
	OrdersRepository orderRepo;
	@Autowired
	OrderService orderService;
	@Autowired
	UsersRepository userRepo;

	@PostMapping("order")
	public OrderDTO postOrder(@RequestBody OrderDTO orderData) {
		System.out.println("con" + orderData);
		Users customer = null;
		if (orderData.getUserId() != null) {
			customer = userRepo.getReferenceById(orderData.getUserId());
		}
		return orderService.postorder(orderData, customer);
	}

	@GetMapping("/user/allOrdersOfAUser/{uid}")
	public List<ResponseOrderDTO> getAllOrders(@PathVariable("uid") Long uid) {
		return orderService.getAllOrders(uid);

	}

	@GetMapping("/singleOrderOfAUser/{oid}")
	public ResponseEntity<ResponseOrderDTO> getOrder(@PathVariable("oid") Long oid) {
		try {
			ResponseOrderDTO getOrder = orderService.getOrder(oid);
			return ResponseEntity.ok(getOrder);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(null);
		}

	}
	
	@DeleteMapping("/cancelOrder/{oid}")
	public  Object cancelOrder(@PathVariable("oid") Long oid) {
		try {
			
			return orderService.DeleteOrder(oid);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(null);
		}
	}

}
