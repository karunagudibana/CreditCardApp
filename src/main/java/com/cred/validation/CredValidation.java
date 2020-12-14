package com.cred.validation;

public class CredValidation {

	public static boolean isNull(String string) {
		if (string == null || string.trim().equals("")) {
			return true;
		}
		return false;
	}

	public static boolean cardNumberSizeValidation(String creditCard) {
		if (size(creditCard) >= 13 && size(creditCard) <= 19) {
			return true;
		}
		return false;
	}

	public static boolean onlyDigits(String str, int n) {
		for (int i = 0; i < n; i++) {

			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static int size(String cardNo) {
		return cardNo.length();
	}

	public static boolean checkLuhn(String cardNo) {
		int nDigits = cardNo.length();

		int nSum = 0;
		boolean isSecond = false;
		for (int i = nDigits - 1; i >= 0; i--) {

			int d = cardNo.charAt(i) - '0';

			if (isSecond == true)
				d = d * 2;

			nSum += d / 10;
			nSum += d % 10;

			isSecond = !isSecond;
		}
		return (nSum % 10 == 0);
	}
}
