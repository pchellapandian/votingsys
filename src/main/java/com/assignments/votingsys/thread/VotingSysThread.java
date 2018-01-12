package com.assignments.votingsys.thread;

import com.assignments.votingsys.constants.Constants;
import com.assignments.votingsys.constants.VotingSystemConfig;
import com.assignments.votingsys.manager.impl.VoteManager;

/**
 * 
 * @author Premnath Chellapandian
 * 
 */
public class VotingSysThread extends Thread {

	private long startTime;
	private long endTime;

	public VotingSysThread() {
		startTime = System.currentTimeMillis();
	}

	@Override
	public void run() {
		while (true) {
			endTime = System.currentTimeMillis();
			if ((endTime - startTime) >= VotingSystemConfig.MAX_MINUTES * 60 * 1000) {
				break;
			}
			if(VotingSystemConfig.IS_MAX_REACHED) {
				break;
			}
		}
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}
}
