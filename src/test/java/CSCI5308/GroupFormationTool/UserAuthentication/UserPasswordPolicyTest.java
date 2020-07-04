package CSCI5308.GroupFormationTool.UserAuthentication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserPasswordPolicyTest {
	
	@Test
	public void setMinLength() {
		IUserPasswordPolicy passwordPolicy = IUserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setMinLength(2);
		assertEquals(passwordPolicy.getMinLength(), 2);
	}

	@Test
	public void setMaxLength() {
		IUserPasswordPolicy passwordPolicy = IUserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setMaxLength(2);
		assertEquals(passwordPolicy.getMaxLength(), 2);
	}

	@Test
	public void setMinUpperCaseLetter() {
		IUserPasswordPolicy passwordPolicy = IUserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setMinUpperCaseLetter(2);
		assertEquals(passwordPolicy.getMinUpperCaseLetter(), 2);
	}

	@Test
	public void setMinLowerCaseLetter() {
		IUserPasswordPolicy passwordPolicy = IUserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setMinLowerCaseLetter(2);;
		assertEquals(passwordPolicy.getMinLowerCaseLetter(), 2);
	}

	@Test
	public void setMinSymbols() {
		IUserPasswordPolicy passwordPolicy = IUserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setMinNoOfSymbols(2);;
		assertEquals(passwordPolicy.getMinNoOfSymbols(), 2);
	}

	@Test
	public void setSymbolsNOtAllowed() {
		IUserPasswordPolicy passwordPolicy = IUserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setNotAllowedCharacters("!@#");;
		assertEquals(passwordPolicy.getNotAllowedCharacters(), "!@#");
	}
	
	@Test
	public void getMinLength() {
		IUserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		
		assertEquals(passwordPolicy.getMinLength(), 2);
	}

	@Test
	public void getMavLength() {
		IUserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		
		assertEquals(passwordPolicy.getMaxLength(), 23);
	}

	@Test
	public void getMinUpperCaseLetter() {
		IUserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		assertEquals(passwordPolicy.getMinUpperCaseLetter(), 1);
	}

	@Test
	public void getMinLowerCaseLetter() {
		IUserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		
		assertEquals(passwordPolicy.getMinLowerCaseLetter(), 1);
	}

	@Test
	public void getMinSymbolsLength() {
		IUserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		
		assertEquals(passwordPolicy.getMinNoOfSymbols(), 1);
	}
	
	@Test
	public void getSymboldnotAllowed() {
		IUserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		
		assertEquals(passwordPolicy.getNotAllowedCharacters(), "@#");
	}
}
