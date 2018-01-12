package com.assignments.votingsys.util;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.assignments.votingsys.constants.Constants;
import com.assignments.votingsys.constants.MotionStatus;
import com.assignments.votingsys.constants.VotingSystemConfig;
import com.assignments.votingsys.controller.ThreadController;
import com.assignments.votingsys.manager.impl.MotionManager;
import com.assignments.votingsys.model.Motion;
import com.assignments.votingsys.model.MotionChoice;

/**
 * 
 * @author Premnath Chellapandian
 * 
 */
public class PrintUtils {

	public static void fill(String symbol, int total) {

		String buffer = new String(Constants.EMPTY);
		for (int i = 0; i < total; i++) {
			buffer += symbol;
		}

		System.out.println(buffer);
	}

	public static String format(String value, int length) {

		int valuelength = 0;
		if (!VotingSysUtils.isEmpty(value)) {
			valuelength = value.length();
			value = " " + value;
		} else {
			value = new String();
		}

		if (valuelength > length) {
			value = value.substring(0, length);
		} else if (valuelength < length) {
			for (int i = 0; i < length - valuelength; i++) {
				value += " ";
			}
		}
		value += "|";
		return value;
	}

	public static synchronized void displayMotionStatus(
			boolean displaychoiceswithresult) {

		Motion motion = MotionManager.getMotion();
		List<MotionChoice> choiceList = motion.getChoiceList();

		System.out.println("\n" + motion.getName());
		if (!displaychoiceswithresult) {
			displayCurrentTime();
			fill("-", 68);
			System.out
					.println("\n| Category          |  Description                                 |");

			fill("-", 68);
			System.out.println();
			for (MotionChoice choice : choiceList) {

				System.out.println("|   " + format(choice.getName(), 15)
						+ format(choice.getDesc(), 45));
			}
			fill("-", 68);
		} else {

			System.out.println("Motion Status : "
					+ motion.getStatus().toString());
			displayVoteStartTime();
			displayVoteEndedTime();
			fill("-", 80);
			System.out
					.println("\n| Category          |  Description                                 | VoteCount |");

			fill("-", 80);
			System.out.println();
			for (MotionChoice choice : choiceList) {

				System.out.println("|   " + format(choice.getName(), 15)
						+ format(choice.getDesc(), 45)
						+ format("" + choice.getVoteCount(), 10));
			}
			fill("-", 80);

		}
	}

	public static void displayResults() {

		int votecountC1 = MotionManager.getMotionChoiceById("C1")
				.getVoteCount();
		int votecountC2 = MotionManager.getMotionChoiceById("C2")
				.getVoteCount();
		if (votecountC1 > votecountC2) {
			MotionManager.getMotion().setStatus(MotionStatus.PASSED);
		} else if (votecountC1 < votecountC2) {
			MotionManager.getMotion().setStatus(MotionStatus.FAILED);
		} else {
			MotionManager.getMotion().setStatus(MotionStatus.TIE);
		}

		displayMotionStatus(true);
	}

	public static void displayWelcomeTitle() {
		System.out
				.println("\n#################Welcome to Voting System#########################################");
	}

	public static void displayVotingCompleteTitle() {

		addLine();
		if (VotingSystemConfig.IS_MAX_REACHED) {
			displayMaxVoteCountReached();
		} else {

			System.out
					.println("\n#################Voting Closed up.#########################################");
		}
		addLine();
	}

	public static void displayVotingProcessInitTitle() {
		addLine();
		System.out
				.println("#################Voting process opened###########################################");
		displayVoteStartTime();
		addLine();
	}

	public static void displayExitTitle() {
		System.out
				.println("\n##########Exited..####################################################################################");
	}

	public static void displayRootMenu() {
		System.out.println();
		System.out.println("1. Press '1' for open vote process");
		System.out.println("2. Press '2' for close and exit vote process");
		System.out.print("Enter your option : ");
	}

	public static void displayResultMenu() {
		System.out.println();
		System.out.println("1. Press '1' for view results");
		System.out.println("2. Press '2' for close and exit vote process");
		System.out.print("Enter your option : ");
	}

	public static void displayMenuForVP() {
		System.out.println();
		System.out.println("1. Press '1' to allow Vice President to Vote");
		System.out.println("2. Press '2' when Vice President not available");
		System.out.print("Enter your option : ");
	}

	public static void displayInputNotValid() {
		System.err.println("Input is not valid.. Try again");
	}

	public static void displayVoterId() {
		System.out
				.print("Enter voter's Id (EmployeeId)                                : ");
	}

	public static void displayVotingChoices() {
		System.out
				.print("Enter your vote (press '1' for 'Yeas', press '2' for 'Nays') : ");
	}

	public static void addLine() {
		System.out.println();
	}

	public static void displaySuccessMessage() {
		System.out.println("Successfully voted..");
	}

	public static void displayVPNotAvailMessage() {
		addLine();
		System.err
				.println("\nMotions has been failed since Vice President is not available for voting in this case");
	}

	public static void displayVoterAlreadyVoted() {
		System.err.println("Voter has already voted..Cannot vote again..");
	}

	public static void displayMaxVoteCountReached() {
		System.out
				.println("\n#################Maximum vote count reached#########################################");
	}

	public static void displayVPCandidateNotAllowed() {
		System.err
				.println("VicePresdient candidate not allowed to vote when motion status is not TIE");
	}

	public static void displayNonVPCanditateNotAllowed() {
		System.err
				.println("Non-VicePresdient candidate not allowed to vote when motion status is not TIE");
	}

	public static void displayNotValidUser() {
		System.err.println("Voter Id is not matching in the system..");
	}

	public static String getTimeInString(long ms) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(ms);
	}

	public static void displayVoteStartTime() {
		System.out.println("Vote Process Opened Time : "
				+ getTimeInString(ThreadController.getStartTime()));

	}

	public static void displayVoteEndedTime() {
		System.out.println("Vote Process Ended Time : "
				+ getTimeInString(ThreadController.getEndTime()));
	}

	public static void displayCurrentTime() {

		long currTime = System.currentTimeMillis();
		System.out.println("Current Time   : " + getTimeInString(currTime));

		long endtime = ThreadController.getStartTime()
				+ VotingSystemConfig.MAX_MINUTES * 60 * 1000;
		long remainingtime = ((endtime - currTime) / 1000);
		System.out.println("End Time       : " + getTimeInString(endtime));
		long mins = TimeUnit.MINUTES.toMinutes(remainingtime / 60);
		long secs = TimeUnit.MINUTES.toMinutes(remainingtime % 60);
		System.out.println("Remaining Time : " + mins + ":"
				+ TimeUnit.SECONDS.toSeconds(secs));
	}
}
