package com.store.greenShoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.DTO.UserDTO;
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
	private ResponseEntity<Object> postUser(@RequestBody UserDTO userDTO) {
		Users user; 
		String userName=userDTO.getUserName();
		user=userRepository.getByUserName(userName);
		if(!(user==null)) {
			System.out.println(user.getUserName());
			return ResponseEntity.badRequest().body("The user is already Present, Failed to Create new User");
		}
		else {
		user=new Users();
		user.setUserName(userName);
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setMobile(userDTO.getMobile());
		user.setRole("User");
		return ResponseEntity.ok(userRepository.save(user));}
	}
	@GetMapping("/userRegistration/{uid}")
	private UserDTO getUser(@PathVariable("uid") Long id) {
		Users user=userRepository.getReferenceById(id);
		UserDTO userDTO=new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setMobile(user.getMobile());
		userDTO.setUserName(user.getUserName());
		return userDTO;
	}
	
	@PostMapping("/userShippingAddress/{uid}")
	private ResponseEntity<Object> postShippingAddress(@RequestBody ShippingAddress address, @PathVariable("uid") Long uid){
		ShippingAddress shippingAddress=shippingRepository.save(address);
		Users user=userRepository.getReferenceById(uid);
		user.setShippingAddress(shippingAddress);
		userRepository.save(user);
		return ResponseEntity.ok(shippingAddress);
		
	}
	
	@PostMapping("/userBillingAddress/{uid}")
	private ResponseEntity<Object> postBillingAddress(@RequestBody BillingAddress address, @PathVariable("uid") Long uid){
		BillingAddress billingAddress = billingAddressRepository.save(address);
		Users user=userRepository.getReferenceById(uid);
		PaymentInformation paymentInformation=user.getPaymentInformation();
		if(paymentInformation!=null) {
			paymentInformation=paymentRepository.getReferenceById(paymentInformation.getPaymentId());
			paymentInformation.setBillingAddress(billingAddress);
			paymentRepository.save(paymentInformation);
		}
		user.setBillingAddress(billingAddress);
		userRepository.save(user);
		return ResponseEntity.ok(billingAddress);
	}
	
	@PostMapping("/userPaymentInformation/{uid}")
	private ResponseEntity<Object> postPayment(@RequestBody PaymentInformation payment,@PathVariable("uid") Long uid){
		PaymentInformation paymentInformation = paymentRepository.save(payment);
		Users user=userRepository.getReferenceById(uid);
		BillingAddress billingAddress=user.getBillingAddress();
		if(billingAddress!=null) {
			paymentInformation=paymentRepository.getReferenceById(uid);
			paymentInformation.setBillingAddress(billingAddress);
			paymentInformation=paymentRepository.save(paymentInformation);
		}
		user.setPaymentInformation(paymentInformation);
		userRepository.save(user);
		return ResponseEntity.ok(paymentInformation);
		
	}
	
	@GetMapping("/userShippingAddress/{uid}")
	private ShippingAddress getShippingAddress(@PathVariable("uid") Long id ) {
		Users user = userRepository.getReferenceById(id);
		return shippingRepository.getReferenceById(user.getShippingAddress().getId());
	}
	@GetMapping("/userBillingAddress/{uid}")
	private BillingAddress getBillingAddress(@PathVariable("uid") Long id ) {
		Users user = userRepository.getReferenceById(id);
		return billingAddressRepository.getReferenceById(user.getBillingAddress().getId());
	}
	@GetMapping("/userPaymentInformation/{uid}")
	private PaymentInformation getPayment(@PathVariable("uid") Long id ) {
		Users user = userRepository.getReferenceById(id);
		return paymentRepository.getReferenceById(user.getPaymentInformation().getPaymentId());
	}
	


}
