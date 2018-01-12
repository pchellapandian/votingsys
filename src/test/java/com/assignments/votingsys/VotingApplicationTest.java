package com.assignments.votingsys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import junit.framework.TestCase;

import org.junit.Test;

import com.assignments.votingsys.constants.Constants;
import com.assignments.votingsys.constants.MotionStatus;
import com.assignments.votingsys.manager.impl.MotionManager;

public class VotingApplicationTest extends TestCase {

	static InputStream in1;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		System.setProperty(Constants.MAX_NUM_OF_VOTES, "10");
		System.setProperty(Constants.MAX_MINUTES, "5");

		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource("test_data/test_data.txt")
				.getFile());

		try {
			System.setIn(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testExecute_MotionFailureTest() {

		VotingApplication.execute();

		assertEquals(MotionStatus.FAILED.toString(), MotionManager.getMotion()
				.getStatus().toString());

	}

	@Test
	public void testExecute_MotionSuccessTest() {

		VotingApplication.execute();

		assertEquals(MotionStatus.PASSED.toString(), MotionManager.getMotion()
				.getStatus().toString());
	}

	@Test
	public void testExecute_PresidentVoteSuccessTest() {

		System.setProperty(Constants.MAX_NUM_OF_VOTES, "11");

		VotingApplication.execute();

		assertEquals(MotionStatus.PASSED.toString(), MotionManager.getMotion()
				.getStatus().toString());

	}

	@Test
	public void testExecute_PresidentVoteNotAvailTest() {

		System.setProperty(Constants.MAX_NUM_OF_VOTES, "11");

		VotingApplication.execute();

		assertEquals(MotionStatus.FAILED.toString(), MotionManager.getMotion()
				.getStatus().toString());

	}
}
