package CSCI5308.GroupFormationTool.GroupFormmer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupFilterModelTest {
	
	@Test
	public void testGetSimilarAndDissimilar() {
		IGroupFilter groupFilter = GroupFilterMockDB.setDefault();
		assertEquals(3, groupFilter.getquestionSimilarOrDissimilar().size());
	}
	@Test
	public void testGetLessthanOrgreateThanx() {
		IGroupFilter groupFilter = GroupFilterMockDB.setDefault();
		assertEquals(1, groupFilter.getConsiderLessThanOrGreaterThanX().size());
	}
	@Test
	public void testSetLessthanOrgreateThanx() {
		IGroupFilter groupFilter = new GroupFilter();
		assertNull(groupFilter.getConsiderLessThanOrGreaterThanX());
		groupFilter.setConsiderLessThanOrGreaterThanX(new HashMap<Integer, ArrayList<Integer>>(){{
				put(1,new ArrayList<Integer>() {{add(1);add(1);}});
			}});
		assertEquals(1, groupFilter.getConsiderLessThanOrGreaterThanX().size());
	}
	@Test
	public void testSetSimilarAndDisimilar() {
		IGroupFilter groupFilter = new GroupFilter();
		assertNull(groupFilter.getquestionSimilarOrDissimilar());
		groupFilter.setquestionSimilarOrDissimilar(new HashMap<Integer, Boolean>() {{
				put(1,true);put(2,false);put(3,true);
			}});
		assertEquals(3, groupFilter.getquestionSimilarOrDissimilar().size());
	}
}
