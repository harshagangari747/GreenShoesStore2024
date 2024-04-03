package com.store.greenShoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.model.BillingAddress;
import com.store.greenShoes.model.PaymentInformation;
import com.store.greenShoes.model.ShippingAddress;
import com.store.greenShoes.model.Users;
import com.store.greenShoes.repository.BillingAddressRepository;
import com.store.greenShoes.repository.PaymentRepository;
import com.store.greenShoes.repository.ShippingAddressRepository;
import com.store.greenShoes.repository.UsersRepository;

@RestController
public class UserController {
	@Autowired
	UsersRepository userRepository;
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	ShippingAddressRepository shippingRepository;
	@Autowired
	BillingAddressRepository billingAddressRepository;
	@PostMapping("/userRegistration")
	private ResponseEntity<Object> postUser(@RequestBody Users user) {
		
//		System.out.println(Base64.getDecoder().decode(code));
//		String username=new String(Base64.getDecoder().decode(code)).split(":")[0];
//		String password=new String(Base64.getDecoder().decode(code)).split(":")[1];
		String existingUser= user.getUserName();
		if(!(existingUser==null)) {
			return ResponseEntity.badRequest().body("The user is already Present, Failed to Create new User");
		}
		return  ResponseEntity.ok(userRepository.save(user));
		}
	
	@PostMapping("/userShippingAddress")
	private ResponseEntity<Object> postShippingAddress(@RequestBody ShippingAddress address){
		return ResponseEntity.ok(shippingRepository.save(address));
		
	}
	
	@PostMapping("/userBillingAddress")
	private ResponseEntity<Object> postBillingAddress(@RequestBody BillingAddress address){
		return ResponseEntity.ok(billingAddressRepository.save(address));
		
	}
	
	@PostMapping("/userPaymentInformation")
	private ResponseEntity<Object> postPayment(@RequestBody PaymentInformation payment){
		return ResponseEntity.ok(paymentRepository.save(payment));
		
	}
	
	@GetMapping("/userShippingAddress/{id}")
	private ShippingAddress getShippingAddress(@PathVariable("id") Long id ) {
		return shippingRepository.getReferenceById(id);
	}
	@GetMapping("/userBillingAddress/{id}")
	private BillingAddress getBillingAddress(@PathVariable("id") Long id ) {
		return billingAddressRepository.getReferenceById(id);
	}
	@GetMapping("/userPaymentInformation/{id}")
	private PaymentInformation getPayment(@PathVariable("id") Long id ) {
		return paymentRepository.getReferenceById(id);
	}
	
//@PostMapping("/adminRegistration")
//private ResponseEntity<Object> postAdmin(@RequestBody Users admin) {
//	
//	Users existingUser= userProfile.getByUserName(admin.getUserName());
//	if(!(existingUser==null)) {
//		System.out.println(existingUser.getUserName());
//		return ResponseEntity.badRequest().body("The Admin is already Present, Failed to Create new User");
//	}
//	
//	
//	return  ResponseEntity.ok(userProfile.save(admin));}
//	

}
