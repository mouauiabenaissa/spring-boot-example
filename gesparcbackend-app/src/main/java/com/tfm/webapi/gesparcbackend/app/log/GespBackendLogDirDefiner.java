package com.tfm.webapi.gesparcbackend.app.log;

import ch.qos.logback.core.PropertyDefinerBase;

public class GespBackendLogDirDefiner extends PropertyDefinerBase {
	
	public static final String GESPBACKEND_LOG_DIR_PROPERTY = "gesparcbackend.log.dir";
	
	@Override
	public String getPropertyValue() {
		return System.getProperty(GESPBACKEND_LOG_DIR_PROPERTY);
	}

}
