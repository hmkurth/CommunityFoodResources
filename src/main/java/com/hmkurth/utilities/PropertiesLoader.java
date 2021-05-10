package com.hmkurth.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * The interface Properties loader.
 */
public interface PropertiesLoader {
    /**
     * The constant logger.
     */
    Logger logger = LogManager.getLogger();

    /**
     * Load properties properties.
     *
     * @param propertiesFilePath the properties file path
     * @return the properties
     * @throws Exception the exception
     */
    default Properties loadProperties(String propertiesFilePath) throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException ioException) {
            logger.debug(ioException);
            throw ioException;
        } catch (Exception exception) {
           logger.debug(exception);
            throw exception;
        }
        return properties;
    }

}
