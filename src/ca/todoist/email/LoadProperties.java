package ca.todoist.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class LoadProperties {
	
	private Properties properties;

	LoadProperties() {
		try {
			properties = load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties load() throws IOException {
		Properties prop = new Properties();
		InputStream in = getClass().getResourceAsStream("/user.properties");
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
	
	public String getProject() {
		return properties.getProperty("project");
	}

}
