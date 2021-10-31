package com.tfm.webapi.gesparcbackend.app.options;

import java.io.File;

import lombok.Getter;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
		name="gesparcbackend",
		sortOptions = false,
		version = { "Version %1$s", "(c) 2021, licensed to TFM-IT" },
		headerHeading = "@|bold,underline Usage|@:%n%n",
		header = "GesparcBackend App",
		synopsisHeading = "%n",
		descriptionHeading = "%n@|bold,underline Description|@:%n%n",
		description = "GesparcBackend permet de gérer les véhicules de manière automatisé via une API web",
		parameterListHeading = "%n@|bold,underline Parameters|@:%n",
		optionListHeading = "%n@|bold,underline Options|@:%n",
		commandListHeading = "%n@|bold,underline Commands|@:%n%nLes commandes disponible via cet outil sont:%n%n",
		showDefaultValues = true,
		requiredOptionMarker = '*',
		footer = "%nCopyright(c) 2021 - TFM-IT (TAPIFY)",
		abbreviateSynopsis = false)
public class GespBackendOptions {
	
	public static final String DEFAULT_LOG_LEVEL   = "INFO";
	
	public static final String DEFAULT_LOG_DIR     = "logs";
	
	public static final String DEFAULT_CONFIG_FILE = "gesparcbackend.conf";
	
	public static final int    DEFAULT_PORT        = 8080;
	
	@Option(names = { "-h", "--help" }, usageHelp = true, description = "Affiche l'aide")
	private boolean helpRequested;
	
	@Option(names = { "-V", "--version" }, versionHelp = true, description = "Affiche la version de l'outil" )
	private boolean versionRequested;
	
	@Getter
	@Option(names = { "--debug" }, description = "Active le debug du moteur spring et tomcat")
	private boolean            debug = false;
	
	@Getter
	@Option(names = { "-c", "--conf" }, paramLabel = "<conf>", description = "Fichier de configuration")
	private File               confFile = new File(DEFAULT_CONFIG_FILE);
	
	@Getter
	@Option(names = { "-p", "--port" }, paramLabel = "<port>", description = "Port d'ecoute")
	private int                port                = DEFAULT_PORT;
	
	@Getter
	@Option(names = { "--log-level" }, paramLabel = "<level>", description = "Specifie le niveau de log. Les valeurs possibles sont OFF|ERROR|WARN|INFO|DEBUG|TRACE")
	private String logLevel                        = DEFAULT_LOG_LEVEL;
	
	@Getter
	@Option(names = { "--log-dir" }, paramLabel = "<dir>", description = "Repertoire d'enregistrement des fichiers de log")
	private String logDir                          = DEFAULT_LOG_DIR;

}
