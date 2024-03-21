package com.store.greenShoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.model.Address;
import com.store.greenShoes.model.Customer;
import com.store.greenShoes.model.PaymentInformation;
import com.store.greenShoes.model.UserProfile;
import com.store.greenShoes.repository.AddressRepository;
import com.store.greenShoes.repository.CustomerRepository;
import com.store.greenShoes.repository.PaymentRepository;
import com.store.greenShoes.repository.UserProfileRepository;

@RestController
public class UserController {
	@Autowired
	UserProfileRepository userProfile;
	@Autowired
	CustomerRepository customer;
	@Autowired
	AddressRepository addressRepo;
	@Autowired
	PaymentRepository paymentRepo;
	@PostMapping("/customerRegistration")
	private ResponseEntity<Object> postUser(@RequestBody Customer user) {
		
		UserProfile profile=user.getUserProfile();
		System.out.println(profile);
//		System.out.println(Base64.getDecoder().decode(code));
//		String username=new String(Base64.getDecoder().decode(code)).split(":")[0];
//		String password=new String(Base64.getDecoder().decode(code)).split(":")[1];
		UserProfile existingUser= userProfile.getByUserName(profile.getUserName());
		if(!(existingUser==null)) {
			System.out.println(existingUser.getUserName());
			return ResponseEntity.badRequest().body("The user is already Present, Failed to Create new User");
		}
		Address address = user.getAddress();
		PaymentInformation payment=user.getPaymentInformation();
		addressRepo.save(address);
		userProfile.save(profile);
		paymentRepo.save(payment);
		return  ResponseEntity.ok(customer.save(user));
		}
		
	
@PostMapping("/adminRegistration")
private ResponseEntity<Object> postAdmin(@RequestBody UserProfile admin) {
	
	UserProfile existingUser= userProfile.getByUserName(admin.getUserName());
	if(!(existingUser==null)) {
		System.out.println(existingUser.getUserName());
		return ResponseEntity.badRequest().body("The Admin is already Present, Failed to Create new User");
	}
	
	
	return  ResponseEntity.ok(userProfile.save(admin));}
	
}


