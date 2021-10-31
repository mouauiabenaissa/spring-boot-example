package com.tfm.webapi.gesparcbackend.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import com.tfm.webapi.gesparcbackend.app.log.GespBackendLogDirDefiner;
import com.tfm.webapi.gesparcbackend.app.options.GespBackendOptions;
import com.tfm.webapi.gesparcbackend.utils.GespBackendConfHelper;
import com.tfm.webapi.gesparcbackend.utils.VersionUtil;

import picocli.CommandLine;
import picocli.CommandLine.Help.Ansi;

@SpringBootApplication
@ServletComponentScan(basePackages = { "com.tfm.webapi.gesparcbackend" })
@ComponentScan(basePackages = { "com.tfm.webapi.gesparcbackend" })
@EnableConfigurationProperties
public class GesparcBackend {
	
	public static void main (String[] args) {
		proxyMain(args);
	}
	
	private static EXIT_CODE proxyMain(String[] args) {
		
		final GespBackendOptions options = new GespBackendOptions();
		final List<CommandLine> commandLines = new ArrayList<>();
		
		try {
			commandLines.addAll(new CommandLine(options).parse(args));
			
		} catch (Exception e) {
			System.err.println(e.getMessage()); // NOSONAR
			System.err.println("Erreur lors du parsing des options et paramètres"); // NOSONAR
			System.err.println("Veuillez lire l'aide de la commande en utilisant l'option '-h' ou '--help'"); // NOSONAR
			return EXIT_CODE.PARSING_OPTIONS;
			
		}
		
		String logLevel = null;
		String logDir = null;
		String configurationFileLocation = null;
		boolean isDebug = false;
		int port = -1;
		
		for (final CommandLine commandLine : commandLines) {
			if (commandLine.isUsageHelpRequested()) {
				// Afficher l'aide si demandée !
				commandLine.usage(System.err);
				return EXIT_CODE.OK;
				
			} else if (commandLine.isVersionHelpRequested()) {
				//Afficher la version si demandée !
				commandLine.printVersionHelp(System.err, Ansi.AUTO, VersionUtil.getAppVersion()); // NOSONAR
				return EXIT_CODE.OK;
			} else {
				
				final Object cmd = commandLine.getCommand();
				
				if (cmd instanceof GespBackendOptions) {
					final GespBackendOptions gespBackendOptions = (GespBackendOptions) cmd;
					logLevel = gespBackendOptions.getLogLevel();
					configurationFileLocation= gespBackendOptions.getConfFile().toString();
					port = gespBackendOptions.getPort();
					isDebug = gespBackendOptions.isDebug();
					logDir = gespBackendOptions.getLogDir();
					
				} else {
					logLevel = GespBackendOptions.DEFAULT_LOG_LEVEL;
					configurationFileLocation = GespBackendOptions.DEFAULT_CONFIG_FILE;
					port = GespBackendOptions.DEFAULT_PORT;
					logDir = GespBackendOptions.DEFAULT_LOG_DIR;
				}
			}
		}
		
		System.setProperty(GespBackendLogDirDefiner.GESPBACKEND_LOG_DIR_PROPERTY, logDir);
		
		// Le logger est initialisé ici afin de permettre que la propriété contenant le répertoire de log soit d'abord
		// initialisé
		final Logger log = LoggerFactory.getLogger(GesparcBackend.class);
		
		log.info("GesparcBackend - {}", VersionUtil.getAppVersion());
		
		log.info("Listening port : {}", port);
		log.info("Configuration file : {}", configurationFileLocation);
		log.info("Settings log level dynamically to : {}", logLevel);
		System.setProperty(GespBackendConfHelper.CONF_FILE_PROPERTY_NAME, configurationFileLocation);
		
		final SpringApplication app = new SpringApplication(GesparcBackend.class);
		final Map<String, Object> appProperties = new HashMap<>();
		appProperties.put("server.port", String.valueOf(port));
		
		if (isDebug && !"TRACE".equals(logLevel)) {
			logLevel = "DEBUG";
		}
		
		if (isDebug) {
			appProperties.put("logging.level.org.springframework", logLevel);
		}
		
		appProperties.put("logging.level.com.tfm.webapi.gesparcbackend", logLevel);
		
		app.setDefaultProperties(appProperties);
		
		app.run(args);
		
		return EXIT_CODE.OK;	
	}	
	
	private enum EXIT_CODE {
		OK, PARSING_OPTIONS, COMMAND_ERROR;
	}
	
}
