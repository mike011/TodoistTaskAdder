package ca.todoist.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
	public void testGetProjects() {
		Projects projects = properties.getProjects();
		assertThat(projects.isEmpty(), is(false));
	}
}
