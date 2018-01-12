package com.assignments.votingsys.controller;

import com.assignments.votingsys.constants.Constants;
import com.assignments.votingsys.constants.VotingSystemConfig;
import com.assignments.votingsys.manager.impl.VoteManager;
import com.assignments.votingsys.util.PrintUtils;
import com.assignments.votingsys.util.VotingSysUtils;

/**
 * 
 * @author Premnath Chellapandian
 * 
 */
public class VotingController {

	public VotingController() {

	}

	public void execute(boolean vicepresent) {

		PrintUtils.displayVotingProcessInitTitle();

		int choice = 0;

		String voterId = null;

		boolean validinput = true;
		do {

			if (VoteManager.maxReached(vicepresent)) {
				break;
			}

			PrintUtils.displayMotionStatus(false);

			do {
				validinput = true;
				// Decorator.displayCurrentTime();
				PrintUtils.displayVoterId();
				voterId = VotingSystemConfig.getScanner().next();

				if (!vicepresent && ThreadController.timeUp()) {
					break;
				}

				if (!vicepresent && VoteManager.isVicePresident(voterId)) {
					PrintUtils.displayVPCandidateNotAllowed();
					validinput = false;
				}
				if (vicepresent && !VoteManager.isVicePresident(voterId)) {
					PrintUtils.displayNonVPCanditateNotAllowed();
					validinput = false;
				}
				if (validinput) {
					validinput = VoteManager.isVoterExist(voterId);
				}

				if (validinput) {
					validinput = VoteManager.isNotVotedAlready(voterId);
				}

			} while (!validinput || VotingSysUtils.isEmpty(voterId));

			if (!vicepresent && ThreadController.timeUp()) {
				break;
			}

			PrintUtils.displayVotingChoices();

			validinput = false;
			do {
				try {
					choice = VotingSystemConfig.getScanner().nextInt();
					if (choice == 1 || choice == 2 || choice == 99) {
						validinput = true;
					} else {
						PrintUtils.displayInputNotValid();
						PrintUtils.displayVotingChoices();
						validinput = false;
					}
				} catch (Exception exp) {
					PrintUtils.displayInputNotValid();
					PrintUtils.displayVotingChoices();
					validinput = false;
				}
			} while (validinput == false);

			if (!vicepresent && ThreadController.timeUp()) {
				break;
			}

			switch (choice) {
			case 1:
				VoteManager.registerVote(voterId, Constants.CHOICE_ONE,
						vicepresent);
				break;
			case 2:
				VoteManager.registerVote(voterId, Constants.CHOICE_TWO,
						vicepresent);
				break;
			default:
				break;
			}

		} while (!ThreadController.timeUp());
	}
}
