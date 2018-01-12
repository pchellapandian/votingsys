package com.assignments.votingsys.model;

import java.util.ArrayList;
import java.util.List;

import com.assignments.votingsys.constants.MotionStatus;

/**
 * 
 * @author Premnath Chellapandian
 * 
 */
public class Motion {

	private String name;
	private MotionStatus status;
	private List<MotionChoice> choiceList = new ArrayList<MotionChoice>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MotionStatus getStatus() {
		return status;
	}

	public void setStatus(MotionStatus status) {
		this.status = status;
	}

	public List<MotionChoice> getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(List<MotionChoice> choiceList) {
		this.choiceList = choiceList;
	}

}
