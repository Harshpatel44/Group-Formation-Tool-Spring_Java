package CSCI5308.GroupFormationTool.UserAuthentication.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserPasswordPolicyDB;

@SpringBootTest
public class UserPasswordPolicyTest {
	
	@Test
	public void setMinLength() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setMinLength(2);
		assertEquals(passwordPolicy.getMinLength(), 2);
	}
	@Test
	public void setMaxLength() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setMaxLength(2);
		assertEquals(passwordPolicy.getMaxLength(), 2);
	}
	@Test
	public void setMinUpperCaseLetter() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setMinUpperCaseLetter(2);
		assertEquals(passwordPolicy.getMinUpperCaseLetter(), 2);
	}
	@Test
	public void setMinLowerCaseLetter() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setMinLowerCaseLetter(2);;
		assertEquals(passwordPolicy.getMinLowerCaseLetter(), 2);
	}
	@Test
	public void setMinSymbols() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setMinNoOfSymbols(2);;
		assertEquals(passwordPolicy.getMinNoOfSymbols(), 2);
	}
	@Test
	public void setSymbolsNOtAllowed() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicy.setInstance(1, 23, 1, 1, 1, "#$%");
		passwordPolicy.setNotAllowedCharacters("!@#");;
		assertEquals(passwordPolicy.getNotAllowedCharacters(), "!@#");
	}
	
	@Test
	public void getMinLength() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		
		assertEquals(passwordPolicy.getMinLength(), 2);
	}
	@Test
	public void getMavLength() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		
		assertEquals(passwordPolicy.getMaxLength(), 23);
	}
	@Test
	public void getMinUpperCaseLetter() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		
		assertEquals(passwordPolicy.getMinUpperCaseLetter(), 1);
	}
	@Test
	public void getMinLowerCaseLetter() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		
		assertEquals(passwordPolicy.getMinLowerCaseLetter(), 1);
	}
	@Test
	public void getMinSymbolsLength() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		
		assertEquals(passwordPolicy.getMinNoOfSymbols(), 1);
	}
	@Test
	public void getSymboldnotAllowed() {
		UserPasswordPolicy passwordPolicy = UserPasswordPolicyDB.getDefault();
		
		assertEquals(passwordPolicy.getNotAllowedCharacters(), "@#");
	}
}
