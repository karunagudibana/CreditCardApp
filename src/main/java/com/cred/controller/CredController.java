package com.cred.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.cred.exception.CredException;
import com.cred.exception.ErrorDetails;
import com.cred.model.Cred;
import com.cred.service.CredService;

@ComponentScan
@RestController
@CrossOrigin
@RequestMapping(value = "/cred", produces = { org.springframework.http.MediaType.APPLICATION_JSON_VALUE })
public class CredController {

	@Autowired(required = true)
	private CredService service;

	public CredService getService() {
		return service;
	}

	public void setService(CredService service) {
		this.service = service;
	}

	@GetMapping(value = "/getall")	
	public ResponseEntity<List<Cred>> getAllCreditCards() throws Exception {
		List<Cred> creditCards = service.getAll();
		return new ResponseEntity<List<Cred>>(creditCards, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveCreditCard(@RequestBody Cred cred,WebRequest request) throws CredException {
		try {
			cred = service.createCred(cred);
			return new ResponseEntity<Cred>(cred, new HttpHeaders(), HttpStatus.CREATED);
		} catch (CredException credException) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), credException.getMessage(), request.getDescription(false));
	         return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
