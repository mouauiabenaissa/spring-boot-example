package com.tfm.webapi.gesparcbackend.utils;

import java.util.Properties;

public class VersionUtil {
	
	private VersionUtil() {
	}
	
	public static String getAppVersion() {
		try {
			Properties pomProperties = new Properties();
			pomProperties.load(VersionUtil.class.getResourceAsStream("/META-INF/maven/com.tfm.webapi.gesparcbackend/gesparcbackend-app/pom.properties"));
			return pomProperties.getProperty("version");			
		} catch (Exception e) {
			return "___unknown_version___";
		}
	}
}
