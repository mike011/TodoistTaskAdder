package ca.todoist.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
		assertThat(properties.getUser(), is("User Name"));
	}

	@Test
	public void testGetPassword() {
		assertThat(properties.getPassword(), is("Super Secret"));
	}

	@Test
	public void testGetProjectsFirst() {
		Map<String, String> projects = properties.getProjects();
		assertThat(projects.isEmpty(), is(false));
		assertThat(projects.get("read"), is("a@b.com"));
	}

	@Test
	public void testGetProjectsSecond() {
		Map<String, String> projects = properties.getProjects();
		assertThat(projects.get("solar masses"), is("b@c.ca"));
	}

	@Test
	public void testGetProjectsThird() {
		Map<String, String> projects = properties.getProjects();
		assertThat(projects.get("cats"), is("342234@adfafda.org"));
	}

}
