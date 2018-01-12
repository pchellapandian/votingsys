package com.assignments.votingsys.manager.impl;

import java.util.TreeMap;

import com.assignments.votingsys.constants.VotingSystemConfig;
import com.assignments.votingsys.controller.ThreadController;
import com.assignments.votingsys.manager.core.IManager;
import com.assignments.votingsys.model.MotionChoice;
import com.assignments.votingsys.model.Voter;
import com.assignments.votingsys.util.PrintUtils;

/**
 * Settingup the voters list with dummy data
 * 
 * @author Premnath Chellapandian
 * 
 */
public class VoteManager implements IManager {

	private static int VOTING_COUNT = 0;

	private static TreeMap<String, Voter> votersTable = new TreeMap<String, Voter>();

	/**
	 * Loads the voters list of dummy data
	 */
	public static void init() {
		
		VOTING_COUNT = 0;

		loadVotersData();
	}

	/**
	 * This method used to register the voter
	 * 
	 * @param voterId
	 * @param choiceId
	 * @param vicepresident
	 */
	public synchronized static void registerVote(String voterId,
			String choiceId, boolean vicepresident) {

		if (maxReached(vicepresident)) {
			PrintUtils.displayMaxVoteCountReached();
			return;
		}

		if (!vicepresident && ThreadController.timeUp()) {
			return;
		}

		if (!vicepresident && isVicePresident(voterId)) {
			return;
		}

		if (isVoterExist(voterId)) {

			MotionChoice choice = MotionManager.getMotionChoiceById(choiceId);
			Voter voter = (Voter) votersTable.get(voterId);

			if (voter.isVoted()) {
				PrintUtils.displayVoterAlreadyVoted();
			} else {
				choice.setVoteCount(choice.getVoteCount() + 1);
				voter.setVoted(true);
				voter.setChoice(choice);
				PrintUtils.displaySuccessMessage();
				VOTING_COUNT++;
			}
		}
	}

	/**
	 * This method validates whether maximum num of vote reached or not
	 * 
	 * @param vicepresident
	 * @return
	 */
	public static boolean maxReached(boolean vicepresident) {

		if (vicepresident
				&& VOTING_COUNT == VotingSystemConfig.MAX_NUM_OF_VOTES) {
			VotingSystemConfig.IS_MAX_REACHED = true;
			return true;
		} else if (!vicepresident
				&& VOTING_COUNT == (VotingSystemConfig.MAX_NUM_OF_VOTES - 1)) {
			VotingSystemConfig.IS_MAX_REACHED = true;
			return true;
		}

		return false;
	}

	/**
	 * This method is used to check whether voter object is vice president or
	 * not
	 * 
	 * @param voterId
	 * @return
	 */
	public static boolean isVicePresident(String voterId) {
		Voter voter = (Voter) votersTable.get(voterId);
		if (voter != null && voter.isVicePresident()) {
			return true;
		}

		return false;
	}

	/**
	 * This method is used to check whether voter has already voted or not
	 * 
	 * @param voterId
	 * @return
	 */
	public static boolean isNotVotedAlready(String voterId) {

		Voter voter = (Voter) votersTable.get(voterId);
		if (voter.isVoted()) {
			PrintUtils.displayVoterAlreadyVoted();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * This method check whether voter is exist or not
	 * 
	 * @param voterId
	 * @return
	 */
	public static boolean isVoterExist(String voterId) {
		if (votersTable.containsKey(voterId)) {
			return true;
		}
		PrintUtils.displayNotValidUser();
		return false;
	}

	/**
	 * This method loads the voter's data
	 */
	private static void loadVotersData() {

		votersTable.clear();

		// Voter Information for Vice President
		Voter voter = new Voter();
		voter.setId("vpuser");
		voter.setName("vpuser");
		voter.setVoted(false);
		voter.setVicePresident(true);
		voter.setChoice(null);

		votersTable.put(voter.getId(), voter);

		int max_no_of_votes = VotingSystemConfig.MAX_NUM_OF_VOTES;
		for (int i = 1; i <= max_no_of_votes - 1; i++) {
			// Voter Information for Non-Vice President
			voter = new Voter();
			voter.setId("user" + i);
			voter.setName("user" + i);
			voter.setVoted(false);
			voter.setVicePresident(false);
			voter.setChoice(null);

			votersTable.put(voter.getId(), voter);
		}

	}
}
