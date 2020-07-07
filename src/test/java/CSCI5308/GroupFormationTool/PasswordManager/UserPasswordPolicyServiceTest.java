package CSCI5308.GroupFormationTool.PasswordManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserPasswordPolicyServiceTest {

    UserPasswordPolicyRepository userPasswordPolicyRepository;
    UserPasswordPolicyService userPasswordPolicyService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        userPasswordPolicyRepository = mock(UserPasswordPolicyRepository.class);
        userPasswordPolicyService = new UserPasswordPolicyService(userPasswordPolicyRepository);
    }


    @Test
    void checkPasswordValidation() {
    }

    @Test
    void getUserPasswordPolicy(){
		when(userPasswordPolicyRepository.getUserPasswordPolicy()).thenReturn(UserPasswordPolicyDB.getDefault());
		System.out.println(UserPasswordPolicyDB.getDefault().getMaxLength());
		System.out.println(userPasswordPolicyRepository.getUserPasswordPolicy().getMaxLength());
		assertThat(userPasswordPolicyService.getUserPasswordPolicy()).isEqualToComparingFieldByField(UserPasswordPolicyDB.getDefault());
    }

    @Test
    void getUserPasswordPolicyStatus() {
        UserPasswordPolicyStatus userPasswordPolicyStatus = new UserPasswordPolicyStatus(1,20,1,1,1,3);
        when(userPasswordPolicyRepository.getUserPasswordPolicyStatus()).thenReturn(userPasswordPolicyStatus);
        assertEquals(userPasswordPolicyStatus,userPasswordPolicyService.getUserPasswordPolicyStatus());
    }
}