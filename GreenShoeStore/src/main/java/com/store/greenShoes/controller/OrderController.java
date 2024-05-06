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

import com.store.greenShoes.Constants.Constants;
import com.store.greenShoes.DTO.OrderDTO;
import com.store.greenShoes.DTO.ResponseOrderDTO;
import com.store.greenShoes.model.Users;
import com.store.greenShoes.repository.OrdersRepository;
import com.store.greenShoes.repository.ProductRepository;
import com.store.greenShoes.repository.UsersRepository;
import com.store.greenShoes.service.MailService;
import com.store.greenShoes.service.OrderService;
import com.store.greenShoes.service.UtilitiesService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })
public class OrderController {
	@Autowired
	OrdersRepository orderRepo;
	@Autowired
	OrderService orderService;
	@Autowired
	UsersRepository userRepo;
	@Autowired
	ProductRepository productRepo;

	@PostMapping("order")
	public ResponseEntity<Object> postOrder(@RequestBody OrderDTO orderData) {
		System.out.println("con" + orderData);
		Users customer = null;
		if (orderData.getUserId() != null) {
			customer = userRepo.getReferenceById(orderData.getUserId());
		}
		try {
			OrderDTO response = orderService.postorder(orderData, customer);
			SendOrderConfirmationMail(response, customer);
			return ResponseEntity.ok(response);

		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("Can't place Order");
		}
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
	public Object cancelOrder(@PathVariable("oid") Long oid) {
		try {

			return orderService.DeleteOrder(oid);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	private void SendOrderConfirmationMail(OrderDTO placedOrder, Users customer) {
		String mailBody;
		try {
			mailBody = PrepareMailBody(placedOrder);
			String mailSubject = String.format(Constants.orderSuccessMailSubject, placedOrder.getOrderId());
			MailService.GetMailServiceObject().sendMail(customer.getEmail(), mailSubject, mailBody);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String PrepareMailBody(OrderDTO order) {
		String mailBody = Constants.successOrderTableHeader;
		String temp = "";
		for (var product : order.getProductWithImageDTO()) {
			String productName = productRepo.getReferenceById(product.getProductId()).getName();
			String cell1 = String.format(Constants.successOrderSingleCell, productName + "-");
			String cell2 = String.format(Constants.successOrderSingleCell, product.getQuantity());
			String row = String.format(Constants.successOrderSingleRow, cell1 + cell2);
			temp += row;
		}
		mailBody += temp;
		mailBody += Constants.successOrderTotalFooter;
		mailBody += String.format(Constants.successOrderTotal, order.getTotal());
		mailBody += String.format(Constants.successOrderMailNote, (int) Math.floor(order.getTotal() * 0.15));
		return mailBody;
	}

}
