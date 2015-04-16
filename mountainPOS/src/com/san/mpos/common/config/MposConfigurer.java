package com.san.mpos.common.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * 
 * <pre>
 * 설명: 
 * </pre>
 *
 * @author iiskaos
 * @version 1.0
 */
public class MposConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(MposConfigurer.class);
	public static long totalTime=0;
	public static double securityTotalTime=0;	public static final String CONTEXT_PARAM_NAME = "mposConfigLocations";	public static final String CONTEXT_ATTRIBUTE_NAME = "config";	public static final String CONFIG_DEFAULT_FILE_NAME = "WEB-INF/resources/prop/mpos.properties";
	public static final String CONTEXT_DELIMITERS = ",; ";
	public static final String CLASSPATH_URL_PREFIX = "classpath:";
	public static final String FILE_URL_PREFIX = "file:";
	
	private static ServletContext context;
	
	private static MposConfigurer config;
	
	private String[] configFiles;
	private CompositeConfiguration configuration = new CompositeConfiguration();
	public static MposConfigurer getInstance() {
		if (config == null)
			config = new MposConfigurer();
		return config;
	}
	
	private MposConfigurer() {
		configFiles = new String[] {CONFIG_DEFAULT_FILE_NAME};
	}
	private MposConfigurer(String smosmgrFilePath) {
		configFiles = new String[] {FILE_URL_PREFIX+smosmgrFilePath};
	}
	private MposConfigurer(String[] configFiles) {
		if (configFiles == null || configFiles.length <= 0)
			configFiles = new String[] {CONFIG_DEFAULT_FILE_NAME};
		
		this.configFiles = configFiles;
	}
	
	
	public static void initConfiguration(ServletContext context) {
		
		MposConfigurer.context = context;
		String contextValue = context.getInitParameter(CONTEXT_PARAM_NAME);
		MposConfigurer configurer = new MposConfigurer(StringUtils.tokenizeToStringArray(contextValue, CONTEXT_DELIMITERS, true, true));
		context.setAttribute(CONTEXT_ATTRIBUTE_NAME, configurer.getConfiguration());
		config = configurer;
	}
	
	public static void destroyConfiguration(ServletContext context) {
		context.removeAttribute(CONTEXT_ATTRIBUTE_NAME);
		config.destroy();
		config = null;
		MposConfigurer.context = null;
	}
	
	
	public Configuration getConfiguration() {
		if (configuration.isEmpty()) {
			for (String configFile : configFiles) {
				if (logger.isDebugEnabled())
					logger.debug("create MLife Configuration of file [" + configFile + "]");
				configuration.addConfiguration(createConfiguration(configFile));
			}
		}
		
		return configuration;
	}
	
	private Configuration createConfiguration(String fileName) {
		try {
			if (fileName.endsWith(".properties"))
				return new PropertiesConfiguration(getFileURL(fileName));
			else if (fileName.endsWith(".xml"))
				return new XMLConfiguration(getFileURL(fileName));
			else
				return new PropertiesConfiguration(getFileURL(fileName));
		} catch (ConfigurationException e) {
			logger.error(e.getMessage(), e);
			return null;
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	private URL getFileURL(String fileName) throws MalformedURLException {
		if (fileName.startsWith(CLASSPATH_URL_PREFIX))
			return ClassUtils.getDefaultClassLoader().getResource(fileName.substring(CLASSPATH_URL_PREFIX.length()));
		else if (fileName.startsWith(FILE_URL_PREFIX))
			return new File(fileName.substring(FILE_URL_PREFIX.length())).toURI().toURL();
		else
			return context.getResource(fileName);
	}
	
	public void destroy() {
		configuration.clear();
	}
}
