package CSCI5308.GroupFormationTool.PasswordManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class UserPasswordPolicyServiceTest {

	IUserPasswordPolicyRepository userPasswordPolicyRepository;
	IUserPasswordPolicyService userPasswordPolicyService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		userPasswordPolicyRepository = mock(UserPasswordPolicyRepository.class);
		UserPasswordManagerAbstractFactory.instance().setPasswordPolicyRepository(userPasswordPolicyRepository);
		userPasswordPolicyService = UserPasswordManagerAbstractFactory.instance().getPasswordPolicyService();
	}

	@Test
	void getUserPasswordPolicy() throws Exception {
		when(userPasswordPolicyRepository.getUserPasswordPolicy()).thenReturn(UserPasswordPolicyDB.getDefault());
		System.out.println(UserPasswordPolicyDB.getDefault().getMaxLength());
		System.out.println(userPasswordPolicyRepository.getUserPasswordPolicy().getMaxLength());
		assertThat(userPasswordPolicyService.getUserPasswordPolicy())
				.isEqualToComparingFieldByField(UserPasswordPolicyDB.getDefault());
	}

	@Test
	void getUserPasswordPolicyStatus() throws Exception {
		UserPasswordPolicyStatus userPasswordPolicyStatus = new UserPasswordPolicyStatus(1, 20, 1, 1, 1, 3);
		when(userPasswordPolicyRepository.getUserPasswordPolicyStatus()).thenReturn(userPasswordPolicyStatus);
		assertEquals(userPasswordPolicyStatus, userPasswordPolicyService.getUserPasswordPolicyStatus());
	}
}