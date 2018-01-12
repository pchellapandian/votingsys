package com.assignments.votingsys.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.assignments.votingsys.constants.Constants;
import com.assignments.votingsys.constants.VotingSystemConfig;
import com.assignments.votingsys.exception.ConfigurationException;

/**
 * Loads the properties data like max_no_of_votes
 * 
 * @author Premnath Chellapandian
 */
public class ApplicationProperties {

	/**
	 * Loads the properties data like max_no_of_votes
	 * 
	 * @throws ConfigurationException
	 */
	public static void init() throws ConfigurationException {

		VotingSystemConfig.init();

		try {
			Properties properties = new Properties();

			InputStream istream = ApplicationProperties.class.getClassLoader()
					.getResourceAsStream(Constants.PROPERTY_FILE_NAME);

			properties.load(istream);
			istream.close();

			// Get the max_no_of_votes from java env variable
			if (!VotingSysUtils.isEmpty(System
					.getProperty(Constants.MAX_NUM_OF_VOTES))) {
				VotingSystemConfig.MAX_NUM_OF_VOTES = (new Integer(
						System.getProperty(Constants.MAX_NUM_OF_VOTES)))
						.intValue();
			} else {
				// If max_no_of_votes is not avail in the env variables,
				// it will pick the default value from the properties
				VotingSystemConfig.MAX_NUM_OF_VOTES = (new Integer(
						(String) properties.get(Constants.MAX_NUM_OF_VOTES)))
						.intValue();
			}

			if (!VotingSysUtils.isEmpty(System
					.getProperty(Constants.MAX_MINUTES))) {
				VotingSystemConfig.MAX_MINUTES = (new Integer(
						System.getProperty(Constants.MAX_MINUTES))).intValue();
			} else {
				// If max_mins is not avail in the env variables,
				// it will pick the default value from the properties
				VotingSystemConfig.MAX_MINUTES = (new Integer(
						(String) properties.get(Constants.MAX_MINUTES)))
						.intValue();
			}

		} catch (FileNotFoundException fne) {
			throw new ConfigurationException(fne.getMessage());
		} catch (IOException ioe) {
			throw new ConfigurationException(ioe.getMessage());
		}
	}
}
