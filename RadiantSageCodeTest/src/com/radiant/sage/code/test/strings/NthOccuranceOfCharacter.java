package com.radiant.sage.code.test.strings;

public class NthOccuranceOfCharacter {

	public static void main(String[] args) {

		try {
			String str = "Who is the expert in the domain of corelab automation industry.";
			char ch = 'e';
			int n = 2;
			System.out.print("Index Position: " +findNthOccurance(str, ch, n));
		} catch (Exception e) {
			System.out.println("Exception occured inside the NthOccuranceOfCharacter main: " + e.getMessage());
		}
	}

	static int findNthOccurance(String str, char ch, int n){
		int occur = 0;
		try {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == ch) {
					occur += 1;
				}
				if (occur == n)
					return i;
			}
		} catch (Exception e) {
			System.out.println("Exception occured inside the NthOccuranceOfCharacter findNthOccurance: " + e.getMessage());
			throw e;
		}
		return -1;
	}
}
