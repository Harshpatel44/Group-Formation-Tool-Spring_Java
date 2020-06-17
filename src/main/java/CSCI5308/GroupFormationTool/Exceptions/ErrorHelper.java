package CSCI5308.GroupFormationTool.Exceptions;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;

public class ErrorHelper {
	public static void rejectErrors(BindingResult result, final List<String> errors) {
	    if (errors != null) {
	        for (final String error : errors) {
	            result.reject(error);
	        }
	    }
	    
	}
	public static void rejectErrors(BindingResult result, final Map<String,String> errors) {
	    if (errors != null) {
	        for (String key : errors.keySet()) {
	            result.rejectValue(key,"message code",errors.get(key));
	        }
	    }
	}

}
