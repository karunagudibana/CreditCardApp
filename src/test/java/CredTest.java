

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cred.Cred1Application;
import com.cred.exception.CredException;
import com.cred.model.Cred;
import com.cred.rep.CredRepository;
import com.cred.service.CredService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Cred1Application.class,CredTest.class,CredService.class,CredRepository.class})
public class CredTest {

	@Autowired
	CredService service;

	@Autowired
	CredRepository repository;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Test
	@DisplayName("/cred save ")
	public void testSave() throws Exception {
		Cred cred = new Cred();
		cred.setCredNum("4571661027381607");
		cred.setGivenName("SBI");
		cred.setCardLimit("10000");
		cred = service.createCred(cred);
		assertEquals(true, cred.getCredId() != 0);
	}
	
	@Test
	@DisplayName("/cred save with card Number empty")
	public void testCardNumberWithNull() throws Exception {
		Cred cred = new Cred();
		thrown.expect(CredException.class);
        thrown.expectMessage("Credit card Number Required");
		cred = service.createCred(cred);
	}
	
	@Test
	@DisplayName("/cred save with Invalid Length")
	public void testCardNumberInvalidLength() throws Exception {
		Cred cred = new Cred();
		cred.setCredNum("45678988");
		thrown.expect(CredException.class);
        thrown.expectMessage("Invalid Credit card Number length");
		cred = service.createCred(cred);
	}
	
	@Test
	@DisplayName("/cred save with string ")
	public void testCardNumberWithString() throws Exception {
		Cred cred = new Cred();
		cred.setCredNum("Chandujjhugjhgg");
		thrown.expect(CredException.class);
        thrown.expectMessage("Credit card Number allow only numbers");
		cred = service.createCred(cred);
	}

	@Test
	@DisplayName("/cred getAll ")
	public void testGetAll() throws Exception {
		List<Cred> credtCards = service.getAll();
		assertNotNull(credtCards);
	}
}