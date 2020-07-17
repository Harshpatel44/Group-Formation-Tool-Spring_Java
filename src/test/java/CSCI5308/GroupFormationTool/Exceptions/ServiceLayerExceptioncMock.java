package CSCI5308.GroupFormationTool.Exceptions;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceLayerExceptioncMock {
    public static ServiceLayerException getServiceLayerExceptionDefault() {
        return new ServiceLayerException() {
            {
                setErrors(new ArrayList<String>() {
                    {
                        add("error1");
                    }
                });
                setMapErrors(new HashMap<String, String>() {
                    {
                        put("1", "error1");
                    }
                });
            }
        };
    }
}
