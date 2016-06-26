package ca.todoist.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

	private Properties properties;
	private Projects projects;

	public LoadProperties() {
		this("/src/main/java/user.properties");
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
		File base = new File(".");
		String file = base.getCanonicalPath() + filename;
		if (!new File(file).exists()) {
			throw new IllegalArgumentException(file + " not found.");
		}				
		FileInputStream in = new FileInputStream(file);
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
