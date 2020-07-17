package CSCI5308.GroupFormationTool.GroupFormmer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupFormmerServiceTest {

	private IGroupFormmerRepo groupFormerRepo;
	private IGroupFormmerService groupFormerService;

	@BeforeEach
	public void init() {

		groupFormerRepo = mock(GroupFormmerRepo.class);
		GroupFormmerAbstractFactory.instance().setGroupFormerRepo(groupFormerRepo);
		groupFormerService = GroupFormmerAbstractFactory.instance().getGroupFormmerService();
	}

	@Test
	public void testSaveGroupFormula() {

		IGroupFilter filter = new GroupFilter();
		when(groupFormerRepo.saveGroupFormula(filter, "101")).thenReturn(true);
		assertEquals(true, groupFormerService.saveGroupFormula(filter, "101"));
	}

	@Test
	public void testSaveGroupFormula2() {

		IGroupFilter filter = new GroupFilter();
		when(groupFormerRepo.saveGroupFormula(filter, "101")).thenReturn(false);
		assertEquals(false, groupFormerService.saveGroupFormula(filter, "101"));
	}

	@Test
	public void testSaveGroupFormula3() {

		IGroupFilter filter = new GroupFilter();
		when(groupFormerRepo.saveGroupFormula(filter, "102")).thenReturn(true);
		assertEquals(true, groupFormerService.saveGroupFormula(filter, "102"));
	}

	@Test
	public void testSaveGroupFormula4() {

		IGroupFilter filter = new GroupFilter();
		when(groupFormerRepo.saveGroupFormula(filter, "102")).thenReturn(false);
		assertEquals(false, groupFormerService.saveGroupFormula(filter, "102"));
	}
	@Test
	public void testGetGroupFormula() {
		
		IGroupFilter filter = new GroupFilter();
		when(groupFormerRepo.getGroupFormula("101")).thenReturn(filter);
		assertThat(filter).isEqualToComparingFieldByField(filter);
	}
	@Test
	public void testGetGroupFormula2() {
		
		IGroupFilter filter = GroupFilterMockDB.setDefault() ;
		when(groupFormerRepo.getGroupFormula("101")).thenReturn(filter);
		assertEquals(1, filter.getConsiderLessThanOrGreaterThanX().size());
		assertThat(filter).isEqualToComparingFieldByField(filter);
	}
	
	
}
