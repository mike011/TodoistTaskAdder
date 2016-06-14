package ca.todoist.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

	private Properties properties;
	private Projects projects;

	public LoadProperties() {
		this("/user.properties");
	}

	public LoadProperties(String filename) {
		try {
			properties = load(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		projects = new Projects();
	}

	public Properties load(String filename) throws IOException {
		Properties prop = new Properties();
		InputStream in = LoadProperties.class.getClass().getResourceAsStream(filename);
		if (null == in) {
			String r = "";
			File file = new File(".");			
			for(String f : file.list()) {
				r+= f + " ";
			}
			throw new IllegalArgumentException(filename + " not found. Dir is " + file.getAbsolutePath() + " and files in dir are: " + r);
		}
		prop.load(in);
		in.close();
		return prop;
	}

	public String getUser() {
		return properties.getProperty("username");
	}

	public String getPassword() {
		return properties.getProperty("password");
	}

	public Projects getProjects() {
		String property = properties.getProperty("projects");
		projects.parse(property);
		return projects;
	}

}
