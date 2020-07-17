package CSCI5308.GroupFormationTool.GroupFormmer;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupFilterMockDB {
	
	public static IGroupFilter setDefault() {
		
		IGroupFilter filter = new GroupFilter() {{
			setquestionSimilarOrDissimilar(new HashMap<Integer, Boolean>() {{
				put(1,true);put(2,false);put(3,true);
			}});
			setConsiderLessThanOrGreaterThanX(new HashMap<Integer, ArrayList<Integer>>(){{
				put(1,new ArrayList<Integer>() {{add(1);add(1);}});
			}});
		}};
		
		return filter;
	}
}
