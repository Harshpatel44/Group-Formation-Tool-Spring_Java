package CSCI5308.GroupFormationTool.Exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceLayerException extends Exception{
	private List<String> errors = new ArrayList<>();
	private Map<String,String> mapErrors = new HashMap<String,String>();
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public Map<String,String> getMapErrors() {
		return mapErrors;
	}
	public void setMapErrors(Map<String,String> mapErrors) {
		this.mapErrors = mapErrors;
	}
}