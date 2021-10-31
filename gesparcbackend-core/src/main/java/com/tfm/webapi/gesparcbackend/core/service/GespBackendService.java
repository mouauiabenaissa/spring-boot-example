package com.tfm.webapi.gesparcbackend.core.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface GespBackendService { // NOSONAR
	
	Logger log = LoggerFactory.getLogger(GespBackendService.class);
	
	Collection<String> getServiceInfos();
	
	Collection<String> getServiceWarnings();
	
	Collection<String> getServiceErrors();
	
	default void clearLogs() {
		if (getServiceInfos() != null) {
			getServiceInfos().clear();
		}
		if (getServiceWarnings() != null) {
			getServiceWarnings().clear();
		}
		if (getServiceErrors() != null) {
			getServiceErrors().clear();
		}		
	}
	
	default void printLogs() {
		
		log.info("--------------------------------------------- {} ----------------------------------------", getClass().getSimpleName());
		
		for (final String error : getServiceErrors()) {
			log.error(error);
		}
		
		for (final String warning : getServiceWarnings()) {
			log.warn(warning);			
		}
		
		for (final String info : getServiceInfos()) {
			log.error(info);
		}
		
		log.info("-------------------------------------------------------------------------------------------------------------");
		
	}
}
