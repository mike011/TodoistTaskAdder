package ca.todoist.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class LoadPropertiesTest {

	private LoadProperties properties;

	@Before
	public void setup() {
		String file = File.separator;
		file += "src" + File.separator;
		file += "test" + File.separator;
		file += "java" + File.separator;
		file += "test" + File.separator;
		file += "test.properties";
		properties = new LoadProperties(file);
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
