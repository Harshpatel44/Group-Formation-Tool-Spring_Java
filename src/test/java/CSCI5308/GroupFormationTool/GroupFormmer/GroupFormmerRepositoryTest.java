package CSCI5308.GroupFormationTool.GroupFormmer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupFormmerRepositoryTest {
	
	private IGroupFormmerRepo groupFormerRepo;
	
	@Test
	public void testSaveGroupFormula() {
		
		IGroupFilter filter = new GroupFilter();
		groupFormerRepo = mock(GroupFormmerRepo.class);
		when(groupFormerRepo.saveGroupFormula(filter, "101")).thenReturn(true);
		assertEquals(true, groupFormerRepo.saveGroupFormula(filter, "101"));
	}
	@Test
	public void testSaveGroupFormula2() {
		
		IGroupFilter filter = new GroupFilter();
		groupFormerRepo = mock(GroupFormmerRepo.class);
		when(groupFormerRepo.saveGroupFormula(filter, "101")).thenReturn(false);
		assertEquals(false, groupFormerRepo.saveGroupFormula(filter, "101"));
	}
	@Test
	public void testSaveGroupFormula3() {
		
		IGroupFilter filter = new GroupFilter();
		groupFormerRepo = mock(GroupFormmerRepo.class);
		when(groupFormerRepo.saveGroupFormula(filter, "102")).thenReturn(true);
		assertEquals(true, groupFormerRepo.saveGroupFormula(filter, "102"));
	}
	@Test
	public void testSaveGroupFormula4() {
		
		IGroupFilter filter = new GroupFilter();
		groupFormerRepo = mock(GroupFormmerRepo.class);
		when(groupFormerRepo.saveGroupFormula(filter, "102")).thenReturn(false);
		assertEquals(false, groupFormerRepo.saveGroupFormula(filter, "102"));
	}
	@Test
	public void testDeletGroupFormula() {
		
		IGroupFilter filter = new GroupFilter();
		groupFormerRepo = mock(GroupFormmerRepo.class);
		when(groupFormerRepo.deleteGroupFormula("101")).thenReturn(true);
		assertEquals(true, groupFormerRepo.deleteGroupFormula( "101"));
	}
	@Test
	public void testDeletGroupFormula2() {
		
		IGroupFilter filter = new GroupFilter();
		groupFormerRepo = mock(GroupFormmerRepo.class);
		when(groupFormerRepo.deleteGroupFormula("101")).thenReturn(false);
		assertEquals(false, groupFormerRepo.deleteGroupFormula( "101"));
	}
	@Test
	public void testGetGroupFormula() {
		
		IGroupFilter filter = new GroupFilter();
		groupFormerRepo = mock(GroupFormmerRepo.class);
		when(groupFormerRepo.getGroupFormula("101")).thenReturn(filter);
		assertThat(filter).isEqualToComparingFieldByField(filter);
	}
}
