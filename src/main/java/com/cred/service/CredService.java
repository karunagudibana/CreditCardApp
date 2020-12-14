package com.cred.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.cred.exception.CredException;
import com.cred.model.Cred;
import com.cred.rep.CredRepository;
import com.cred.validation.CredValidation;

@Service
@ComponentScan
@EnableAutoConfiguration
@org.springframework.transaction.annotation.Transactional
public class CredService {

	@Autowired(required = true)
	@Qualifier("credRepository")
	private CredRepository credRepository;

	public CredRepository getCredRepository() {
		return credRepository;
	}

	public void setCredRepository(CredRepository credRepository) {
		this.credRepository = credRepository;
	}

	public Cred createCred(Cred cred) throws CredException {
		Cred cred1 = null;
		if(CredValidation.isNull(cred.getCredNum())){
			throw new CredException("Credit card Number Required");
		}		
		if(!CredValidation.cardNumberSizeValidation(cred.getCredNum())){
			throw new CredException("Invalid Credit card Number length");
		}
		if(!CredValidation.onlyDigits(cred.getCredNum(), cred.getCredNum().length())){
			throw new CredException("Credit card Number allow only numbers");
		}
		if(CredValidation.isNull(cred.getGivenName())){
			throw new CredException("Name Required");
		}
		if (CredValidation.checkLuhn(cred.getCredNum())) {
			cred1 = credRepository.save(cred);
			return cred1;
		} else {
			throw new CredException("Invalid Cred Number");
		}

	}

	public List<Cred> getAll() throws CredException {
		List<Cred> credList = (List<Cred>) credRepository.findAll();
		return credList;
	}

}
