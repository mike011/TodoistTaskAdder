package ca.todoist.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class LoadPropertiesTest {

	private LoadProperties properties;

	@Before
	public void setup() {
		properties = new LoadProperties("/test/test.properties");
	}
	
	@Test
	public void testGetUser() {
		assertEquals("User Name", properties.getUser());
	}

	@Test
	public void testGetPassword() {
		assertEquals("Super Secret", properties.getPassword());
	}
	
	@Test
	public void testGetProjectsFirst() {
		Map<String, String> projects = properties.getProjects();
		assertFalse(projects.isEmpty());
		assertEquals("a@b.com", projects.get("read"));
	}
	
	@Test
	public void testGetProjectsSecond() {
		Map<String, String> projects = properties.getProjects();
		assertEquals("b@c.ca", projects.get("solar masses"));
	}
	
	@Test
	public void testGetProjectsThird() {
		Map<String, String> projects = properties.getProjects();
		assertEquals("342234@adfafda.org", projects.get("cats"));
	}

}
