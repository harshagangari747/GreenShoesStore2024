package com.store.greenShoes.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.model.PaymentRequest;
import com.store.greenShoes.model.PaymentResponse;

@RestController
@CrossOrigin(origins="http://localhost:3000",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class PaymentController {

    @PostMapping("/user/simulatePayment")
    public PaymentResponse simulatePayment(@RequestBody PaymentRequest paymentRequest) {
        // Simulate payment logic. In a real scenario, this would interact with a payment gateway.
        System.out.println("Processing payment for amount: " + paymentRequest.getAmount());

        // Simulate a successful payment response
        return new PaymentResponse(true, "Payment processed successfully");
    }
}
