package com.tfm.webapi.gesparcbackend.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GespBackendConfHelper {
	
	private static final Properties PROPS;
	
	private static boolean          initialized             = false;
	
	public static final String      CONF_FILE_PROPERTY_NAME = "gesparcbackend_conf_file";
	
	static {
		PROPS = new Properties();
	}
	
	private GespBackendConfHelper() {
	}
	
	public static String getValue(final String key) {
		if (!initialized) {
			try {
				initialize();
			} catch (IOException e) {
				log.error("Erreur lors de la récupération de valeur à partir du fichier de configuration : {} ", e.getMessage(), e);
			}			
		}
		return PROPS.getProperty(key);
	}
	
	private static void initialize() throws IOException {
		final String confFileLocation = System.getProperty(CONF_FILE_PROPERTY_NAME);
		if (StringUtils.isBlank(confFileLocation)) {
			throw new IllegalStateException("Le fichier de configuration de l'application n'est pas défini !");
		}
		final File confFile = new File(confFileLocation);
		if (!confFile.isFile() || !confFile.canRead()) {
			throw new FileNotFoundException("Le fichier de conf n'esxiste pas ou n'est pas un fichier ou n'est pas accessible en lecture : " + confFile);
		}
		try (final InputStream in = new BufferedInputStream(new FileInputStream(confFile))) {
			PROPS.load(in);
		}
		initialized = true;	
	}
}
