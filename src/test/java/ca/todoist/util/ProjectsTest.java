package ca.todoist.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProjectsTest {

	@Test
	public void testGetProjectsFirst() {
		Projects projects = new Projects();
		String properties = "read=a@b.com";
		projects.parse(properties);
		
		assertThat(projects.get("read"), is("a@b.com"));
	}
	
	@Test
	public void testGetProjectsFirstDifferentCaseOnGet() {
		Projects projects = new Projects();
		String properties = "read=a@b.com";
		projects.parse(properties);
		assertThat(projects.get("READ"), is("a@b.com"));
	}

	@Test
	public void testGetProjectsFirstDifferentCaseOnSet() {
		Projects projects = new Projects();
		String properties = "ReAd=a@b.com";
		projects.parse(properties);
		assertThat(projects.get("READ"), is("a@b.com"));
	}
	
	@Test
	public void testGetProjectsFirstDifferentCaseOnKeys() {
		Projects projects = new Projects();
		String properties = "read=A@B.COM";
		projects.parse(properties);
		assertThat(projects.get("read"), is("a@b.com"));
	}
	
	@Test
	public void testGetProjectsWithEmptySpaces() {
		Projects projects = new Projects();
		String properties = "  read=a@b.com ,  solar masses=b@c.ca  ";
		projects.parse(properties);
		assertThat(projects.get("read"), is("a@b.com"));
	}

	
	@Test
	public void testGetProjectsSecond() {
		Projects projects = new Projects();
		String properties = "read=a@b.com,solar masses=b@c.ca";
		projects.parse(properties);
		assertThat(projects.get("solar masses"), is("b@c.ca"));
	}

	@Test
	public void testGetProjectsThird() {
		Projects projects = new Projects();
		String properties = "read=a@b.com,solar masses=b@c.ca,cats=342234@adfafda.org";
		projects.parse(properties);
		assertThat(projects.get("cats"), is("342234@adfafda.org"));
	}

}
