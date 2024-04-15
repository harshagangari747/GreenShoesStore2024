package com.store.greenShoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.greenShoes.model.BillingAddress;
import com.store.greenShoes.model.PaymentInformation;
import com.store.greenShoes.model.ShippingAddress;
import com.store.greenShoes.model.Users;
import com.store.greenShoes.repository.BillingAddressRepository;
import com.store.greenShoes.repository.PaymentRepository;
import com.store.greenShoes.repository.ShippingAddressRepository;
import com.store.greenShoes.repository.UsersRepository;
@Service
public class UserService {
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	ShippingAddressRepository shippingRepository;
	@Autowired
	BillingAddressRepository billingAddressRepository;
	@Autowired
	UsersRepository userRepository;
	public ResponseEntity<Object> postShippingAddress(ShippingAddress address, Long uid) {
		ShippingAddress shippingAddress=shippingRepository.save(address);
		Users user=userRepository.getReferenceById(uid);
		user.setShippingAddress(shippingAddress);
		userRepository.save(user);
		return ResponseEntity.ok(shippingAddress);
		
		
	}
	public ResponseEntity<Object> postBillingAddress(BillingAddress address, Long uid) {
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
	public ResponseEntity<Object> postPayment(PaymentInformation payment, Long uid) {
		PaymentInformation paymentInformation = paymentRepository.save(payment);
		Users user=userRepository.getReferenceById(uid);
		BillingAddress billingAddress=user.getBillingAddress();
		if(billingAddress!=null) {
			paymentInformation=paymentRepository.getReferenceById(paymentInformation.getPaymentId());
			
			paymentInformation.setBillingAddress(billingAddress);
			paymentInformation=paymentRepository.save(paymentInformation);
		}
		user.setPaymentInformation(paymentInformation);
		userRepository.save(user);
		return ResponseEntity.ok(paymentInformation);
	}

}
