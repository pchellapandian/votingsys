package com.assignments.votingsys.manager.impl;

import com.assignments.votingsys.constants.Constants;
import com.assignments.votingsys.constants.MotionStatus;
import com.assignments.votingsys.manager.core.IManager;
import com.assignments.votingsys.model.Motion;
import com.assignments.votingsys.model.MotionChoice;

/**
 * This calss holds the Motion details
 * 
 * @author Premnath Chellapandian
 * 
 */
public class MotionManager implements IManager {

	private static Motion motion = new Motion();

	/**
	 * Initializes the motion and choice data like yeas and nays
	 */
	public static void init() {
		
		motion = new Motion();

		motion.setName("Motion on implementing the plan 'ABC'");
		motion.setStatus(MotionStatus.VOTING);

		motion.getChoiceList().clear();

		MotionChoice choiceA = new MotionChoice();
		choiceA.setId(Constants.CHOICE_ONE);
		choiceA.setName(Constants.YEAS);
		choiceA.setDesc("Candidates accepting the motion 'ABC'");
		choiceA.setVoteCount(Constants.NUMBER_ZERO);

		MotionChoice choiceB = new MotionChoice();
		choiceB.setId(Constants.CHOICE_TWO);
		choiceB.setName(Constants.NAYS);
		choiceB.setDesc("Candidates not accepting the motion 'ABC'");
		choiceB.setVoteCount(Constants.NUMBER_ZERO);

		motion.getChoiceList().add(choiceA);
		motion.getChoiceList().add(choiceB);
	}

	/**
	 * Will get the Motion object
	 * 
	 * @return
	 */
	public static Motion getMotion() {
		return motion;
	}

	/**
	 * Will get the MotionChoice object depends on id
	 * 
	 * @param id
	 * @return
	 */
	public static MotionChoice getMotionChoiceById(String id) {

		for (MotionChoice choice : motion.getChoiceList()) {
			if (choice.getId().equals(id)) {
				return choice;
			}
		}

		return null;
	}
}
