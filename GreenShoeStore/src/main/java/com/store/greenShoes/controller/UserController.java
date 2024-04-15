package com.store.greenShoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.store.greenShoes.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:3000",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class UserController {
	@Autowired
	UsersRepository userRepository;
	@Autowired
	UserService userService;
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	ShippingAddressRepository shippingRepository;
	@Autowired
	BillingAddressRepository billingAddressRepository;
	@PostMapping("/userRegistration")
	private ResponseEntity<Object> postUser(@RequestBody UserDTO userDTO) {
		Users user; 
		String email=userDTO.getEmail();
		user = userRepository.getByEmail(email);
		if(!(user==null)) {
			System.out.println(user.getEmail());
			return ResponseEntity.badRequest().body("The email is already Present, Failed to Create new User");
		}
		String userName=userDTO.getUserName();
		user = userRepository.getByUserName(userName);
		if(user!=null) {
			System.out.println(user.getUserName());
			return ResponseEntity.badRequest().body("The userName is already Present, Failed to Create new User");
		}
		else {
		user=new Users();
		user.setUserName(userDTO.getUserName());
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
		userDTO.setBillingAddress(user.getBillingAddress());
		userDTO.setPaymentInformation(user.getPaymentInformation());
		userDTO.setShippingAddress(user.getShippingAddress());
		userDTO.setUserId(user.getUserId());
		userDTO.setPassword(user.getPassword());
		return userDTO;
	}
	@GetMapping("/checkEmailExists/{email}")
	private ResponseEntity<Object> checkEmailExists(@PathVariable("email") String email){
		Users user = userRepository.getByEmail(email);
		if(!(user==null)) {
			System.out.println(user.getEmail());
			return ResponseEntity.badRequest().body("The email is already Present, Failed to Create new User");
		}
		return   ResponseEntity.ok(HttpStatus.OK);
		
	}
	
	@PostMapping("/userShippingAddress/{uid}")
	private ResponseEntity<Object> postShippingAddress(@RequestBody ShippingAddress address, @PathVariable("uid") Long uid){
		return userService.postShippingAddress(address,uid);
		
	}
	
	@PostMapping("/userBillingAddress/{uid}")
	private ResponseEntity<Object> postBillingAddress(@RequestBody BillingAddress address, @PathVariable("uid") Long uid){
		return userService.postBillingAddress(address,uid);
		
	}
	
	@PostMapping("/userPaymentInformation/{uid}")
	private ResponseEntity<Object> postPayment(@RequestBody PaymentInformation payment,@PathVariable("uid") Long uid){
		return userService.postPayment(payment,uid);
		
		
	}
	@PutMapping("updateUserInformation/{uid}")
	private UserDTO updateUserInformation( @RequestBody UserDTO userDTO,@PathVariable("uid") Long uid ) {
		if(userDTO.getShippingAddress()!=null) {
		userService.postShippingAddress(userDTO.getShippingAddress(),uid);}
		if(userDTO.getBillingAddress()!=null) {
		userService.postBillingAddress(userDTO.getBillingAddress(), uid);}
		if(userDTO.getPaymentInformation()!=null) {
		userService.postPayment(userDTO.getPaymentInformation(), uid);}
		Users user=userRepository.getReferenceById(uid);
		user.setPassword(userDTO.getPassword());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setMobile(userDTO.getMobile());
		userRepository.save(user);
		userDTO.setUserId(uid);
//		user.setBillingAddress(user.getBillingAddress());
//		user.setPaymentInformation(user.getPaymentInformation());
//		user.setShippingAddress(user.getShippingAddress());
		//user.setShippingAddress(userDTO.getShippingAddress());
//		user.setBillingAddress(userDTO.getBillingAddress());
//		user.setPaymentInformation(userDTO.getPaymentInformation());
		return userDTO;
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
