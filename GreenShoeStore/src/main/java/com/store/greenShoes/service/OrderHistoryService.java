//package com.store.greenShoes.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.store.greenShoes.model.Customer;
//import com.store.greenShoes.model.OrderHistory;
//import com.store.greenShoes.model.Orders;
//import com.store.greenShoes.repository.OrderHistoryRepository;
//@Service
//public class OrderHistoryService {
//	@Autowired
//	OrderHistoryRepository orderHistoryRepo;
//
//	public OrderHistory postorder(Orders orderData, Customer customer) {
//		OrderHistory order=new OrderHistory();
//		order.setCustomer(customer);
//		order.setOrder(orderData);
//		
//		return orderHistoryRepo.save(order);
//	}
//
//}
