package com.assignments.votingsys.model;

/**
 * 
 * @author Premnath Chellapandian
 * 
 */
public class Voter {

	private String id;
	private String name;
	private MotionChoice choice;
	boolean voted;
	boolean isVicePresident;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MotionChoice getChoice() {
		return choice;
	}

	public void setChoice(MotionChoice choice) {
		this.choice = choice;
	}

	public boolean isVoted() {
		return voted;
	}

	public void setVoted(boolean voted) {
		this.voted = voted;
	}

	public boolean isVicePresident() {
		return isVicePresident;
	}

	public void setVicePresident(boolean isVicePresident) {
		this.isVicePresident = isVicePresident;
	}
}
