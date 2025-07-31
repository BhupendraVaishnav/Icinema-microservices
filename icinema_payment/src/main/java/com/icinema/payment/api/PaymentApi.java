package com.icinema.payment.api;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.icinema.payment.dto.PaymentDTO;
import com.icinema.payment.dto.PaymentResponseDTO;

public class PaymentApi {
@PostMapping("")
public ResponseEntity<PaymentResponseDTO> makePayment(@RequestBody PaymentDTO body){
	try {
		Thread.sleep(4000);
	}catch (Exception e) {}
	if(body.getAmount()==null||body.getAmount()<=0.0 ||body.getTime()==null)
		return new ResponseEntity<PaymentResponseDTO>(new PaymentResponseDTO("Payment failed", LocalDateTime.now()),HttpStatus.BAD_REQUEST);
				return new ResponseEntity<PaymentResponseDTO>(new PaymentResponseDTO("Payment success", LocalDateTime.now()),HttpStatus.OK);
}
}
