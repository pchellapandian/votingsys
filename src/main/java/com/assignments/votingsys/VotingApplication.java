package com.assignments.votingsys;

import com.assignments.votingsys.controller.BalletController;
import com.assignments.votingsys.exception.ConfigurationException;
import com.assignments.votingsys.manager.impl.MotionManager;
import com.assignments.votingsys.manager.impl.VoteManager;
import com.assignments.votingsys.util.ApplicationProperties;

/**
 * This is the main class to invoke this application
 * 
 * @author Premnath Chellapandian
 * 
 */
public class VotingApplication {

	/**
	 * This is the main method to invoke this application
	 * 
	 * @param args
	 */
	public static void execute() {

		try {

			// Loads the properties data like max_no_of_votes
			ApplicationProperties.init();

			// Initializes the motion and choice data like yeas and nays
			MotionManager.init();

			// Settingup the voters list with dummy data
			VoteManager.init();

			// Executes and initialize the voting process
			BalletController.execute();

		} catch (ConfigurationException ce) {
			ce.printStackTrace();
		}
	}

	public static void main(String[] args) {

		VotingApplication.execute();
	}
}
