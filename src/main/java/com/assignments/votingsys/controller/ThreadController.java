package com.assignments.votingsys.controller;

import com.assignments.votingsys.constants.VotingSystemConfig;
import com.assignments.votingsys.thread.VotingSysThread;

/**
 * 
 * @author Premnath Chellapandian
 * 
 */
public class ThreadController {

	static VotingSysThread thread = new VotingSysThread();

	public static void run() {

		if (!thread.isAlive()) {
			thread.start();
		}
	}

	public static long getStartTime() {
		return thread.getStartTime();
	}

	public static long getEndTime() {
		return thread.getEndTime();
	}

	public static boolean timeUp() {

		long currTime = System.currentTimeMillis();
		long endtime = getStartTime() + VotingSystemConfig.MAX_MINUTES * 60
				* 1000;
		long remainingtime = ((endtime - currTime) / 1000);
		if (remainingtime <= 0) {
			return true;
		}
		return false;
	}
}
