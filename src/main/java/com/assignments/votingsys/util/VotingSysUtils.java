package com.assignments.votingsys.util;

import com.assignments.votingsys.constants.Constants;

/**
 * 
 * @author Premnath Chellapandian
 * 
 */
public class VotingSysUtils {

	public static boolean isEmpty(Object obj) {

		if (obj == null) {
			return Boolean.TRUE;
		}

		if (obj instanceof String) {
			String var = (String) obj;
			if (var.trim().equals(Constants.EMPTY)) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		}

		return Boolean.FALSE;
	}

}
