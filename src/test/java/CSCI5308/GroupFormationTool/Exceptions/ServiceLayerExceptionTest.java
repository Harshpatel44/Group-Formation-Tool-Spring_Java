package CSCI5308.GroupFormationTool.Exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserMockDB;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

@SpringBootTest
public class ServiceLayerExceptionTest {

	@Test
	void setErrors() {
		List<String> errors = new ArrayList<String>() {
			{
				add("error1");
			}
		};
		ServiceLayerException serviceLayerException = new ServiceLayerException();
		serviceLayerException.setErrors(errors);
		assertEquals(serviceLayerException.getErrors(), errors);
	}

	@Test
	void setMapErrors() {
		Map<String, String> errors = new HashMap<String, String>() {
			{
				put("1", "error1");
			}
		};
		ServiceLayerException serviceLayerException = new ServiceLayerException();
		serviceLayerException.setMapErrors(errors);
		assertEquals(serviceLayerException.getMapErrors(), errors);
	}

	@Test
	void getErrors() {

		ServiceLayerException serviceLayerException = ServiceLayerExceptioncMock.getServiceLayerExceptionDefault();

		assertEquals(serviceLayerException.getErrors(), new ArrayList<String>() {
			{
				add("error1");
			}
		});
	}

	@Test
	void getMapErrors() {

		ServiceLayerException serviceLayerException = ServiceLayerExceptioncMock.getServiceLayerExceptionDefault();

		assertEquals(serviceLayerException.getMapErrors(), new HashMap<String, String>() {
			{
				put("1", "error1");
			}
		});
	}
}
