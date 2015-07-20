package com.web.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.web.bean.SystemConfig;

public class SystemConfigUtil {
	private static final String CONFIG_FILE_NAME = "config.properties";
	private static SystemConfig config = init();
	
	private static SystemConfig init(){
		Configuration configuration;
		try {
			configuration = new PropertiesConfiguration(CONFIG_FILE_NAME);
		} catch (ConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		
		SystemConfig systemConfig = new SystemConfig();
		systemConfig.setName(configuration.getString("name"));
		systemConfig.setVersion(configuration.getString("version"));
		
		return systemConfig;
	}
	
	public SystemConfig getSystemConfig(){
		return config;
	}
	
	public  static String getName(){
		return config.getName();
	}
	
	public static String getVersion(){
		return config.getVersion();
	}
}

