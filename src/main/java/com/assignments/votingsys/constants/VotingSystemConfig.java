package com.assignments.votingsys.constants;

import java.util.Scanner;

/**
 * 
 * @author Premnath Chellapandian
 * 
 */
public class VotingSystemConfig {

	public static int MAX_NUM_OF_VOTES;

	public static int MAX_MINUTES;

	public static boolean IS_MAX_REACHED = false;

	private static Scanner scan = new Scanner(System.in);

	public static void init() {

		MAX_NUM_OF_VOTES = 0;
		MAX_MINUTES = 0;
		IS_MAX_REACHED = false;
	}

	public static Scanner getScanner() {
		return scan;
	}
}
