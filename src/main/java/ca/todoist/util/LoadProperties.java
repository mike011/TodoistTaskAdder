package ca.todoist.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class LoadProperties {

	private Properties properties;

	public LoadProperties() {
		this("/user.properties");
	}

	public LoadProperties(String filename) {
		try {
			properties = load(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties load(String filename) throws IOException {
		Properties prop = new Properties();
		InputStream in = getClass().getResourceAsStream(filename);
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

	public Map<String, String> getProjects() {
		TreeMap<String, String> projects = new TreeMap<String, String>();
		String property = properties.getProperty("projects");
		String[] projectsAndEmails = property.split(",");
		for (String projectAndEmail : projectsAndEmails) {
			String[] split = projectAndEmail.split("=");
			projects.put(split[0], split[1]);
		}
		return projects;
	}

}
