package com.assignments.votingsys.controller;

import com.assignments.votingsys.constants.MotionStatus;
import com.assignments.votingsys.constants.VotingSystemConfig;
import com.assignments.votingsys.manager.impl.MotionManager;
import com.assignments.votingsys.processor.ResultsProcessor;
import com.assignments.votingsys.util.PrintUtils;

/**
 * Executes and initialize the voting process
 * 
 * @author Premnath Chellapandian
 * 
 */
public class BalletController {

	/**
	 * Executes voting and results in this method
	 */
	public static void execute() {

		doCandidateVoting();

		doProcessResults();

		if (isMotionStatusTIE()) {
			doPresidentVoting();
		}
	}

	/**
	 * This method executed the voting process
	 */
	private static void doCandidateVoting() {

		boolean vicePresident = false;

		PrintUtils.displayWelcomeTitle();

		PrintUtils.displayRootMenu();

		int choice = 0;
		boolean validinput = false;
		do {
			try {

				choice = VotingSystemConfig.getScanner().nextInt();

				if (choice == 1 || choice == 2) {
					validinput = true;
				} else {
					PrintUtils.displayInputNotValid();
					PrintUtils.displayRootMenu();
					validinput = false;
				}
			} catch (Exception exp) {
				PrintUtils.displayInputNotValid();
				PrintUtils.displayRootMenu();
				validinput = false;
			}
		} while (validinput == false);
		switch (choice) {
		case 1:

			ThreadController.run();

			VotingController controller = new VotingController();
			controller.execute(vicePresident);
			break;
		case 2:
			// exit
			PrintUtils.displayExitTitle();
			System.exit(0);
			break;
		default:
			break;
		}

		PrintUtils.displayVotingCompleteTitle();
	}

	/**
	 * Executes the results
	 */
	private static void doProcessResults() {

		int choice = 0;
		boolean validinput = false;
		PrintUtils.displayResultMenu();
		do {
			try {
				choice = VotingSystemConfig.getScanner().nextInt();
				if (choice == 1 || choice == 2) {
					validinput = true;
				} else {
					PrintUtils.displayInputNotValid();
					PrintUtils.displayResultMenu();
					validinput = false;
				}
			} catch (Exception exp) {
				PrintUtils.displayInputNotValid();
				PrintUtils.displayResultMenu();
				validinput = false;
			}
		} while (validinput == false);
		switch (choice) {
		case 1:
			ResultsProcessor.publishResults();
			break;
		case 2:
			PrintUtils.displayExitTitle();
			break;
		default:
			break;
		}
	}

	/**
	 * Executes the Vicepresident to vote incase of TIE
	 */
	private static void doPresidentVoting() {

		int choice = 0;
		boolean validinput = false;
		boolean vicepres = true;

		PrintUtils.displayMenuForVP();

		do {
			try {
				choice = VotingSystemConfig.getScanner().nextInt();
				if (choice == 1 || choice == 2) {
					validinput = true;
				} else {
					PrintUtils.displayInputNotValid();
					PrintUtils.displayMenuForVP();
					validinput = false;
				}
			} catch (Exception exp) {
				PrintUtils.displayInputNotValid();
				PrintUtils.displayMenuForVP();
				validinput = false;
			}
		} while (validinput == false);
		switch (choice) {
		case 1:

			ResultsProcessor.publishResults();

			VotingController controller = new VotingController();
			controller.execute(vicepres);

			ResultsProcessor.publishResults();

			break;
		case 2:

			// If the VP is not avail, making the motions as FAILED
			MotionManager.getMotion().setStatus(MotionStatus.FAILED);
			
			PrintUtils.displayVPNotAvailMessage();

			PrintUtils.displayMotionStatus(true);

			PrintUtils.displayExitTitle();
			break;
		default:
			break;
		}
	}

	private static boolean isMotionStatusTIE() {

		if (MotionManager.getMotion().getStatus() == MotionStatus.TIE) {
			return true;
		}
		return false;
	}
}
