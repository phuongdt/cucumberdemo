package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class PropertiesUtil {
	public static Properties load_config_sys() throws IOException{
		FileInputStream is = new FileInputStream(System.getProperty("user.dir") + File.separator+ "configuration.properties");
		Properties pros = new Properties();
		pros.load(is);
		return pros;
	}
}
