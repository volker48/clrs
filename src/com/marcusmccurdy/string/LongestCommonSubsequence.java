package com.marcusmccurdy.string;

/**
 *
 * @author Marcus McCurdy <marcus.mccurdy@gmail.com>
 */
public class LongestCommonSubsequence {

	private enum Arrow {
		UP_LEFT, UP, LEFT
	}

	public String calculate(String first, String second) {
		final StringBuilder output = new StringBuilder();
		Arrow[][] b = new Arrow[first.length()+1][second.length()+1];
		int[][] lcsLengthTable = new int[first.length()+1][second.length()+1];
		for (int i = 1; i <= first.length(); i++) {
			lcsLengthTable[i][0] = 0;
		}
			for (int j = 0; j <= second.length(); j++) {
			lcsLengthTable[0][j] = 0;
		}
		for (int i = 1; i <= first.length(); i++) {
			for (int j = 1; j <= second.length(); j++) {
				if (first.charAt(i-1) == second.charAt(j-1)) {
					lcsLengthTable[i][j] = lcsLengthTable[i - 1][j - 1] + 1;
					b[i][j] = Arrow.UP_LEFT;
				} else if (lcsLengthTable[i-1][j] >= lcsLengthTable[i][j-1]) {
					lcsLengthTable[i][j] = lcsLengthTable[i-1][j];
					b[i][j] = Arrow.UP;
				} else {
					lcsLengthTable[i][j] = lcsLengthTable[i][j-1];
					b[i][j] = Arrow.LEFT;
				}
			}
		}

		printLcs(b, first, first.length(), second.length(), output);
		return output.toString();
	}

	public static void main(String[] args) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		System.out.println(lcs.calculate("mathematician", "magician"));
	}

	private void printLcs(Arrow[][] b, String first, int i, int j, StringBuilder output) {
		if (i == 0 || j == 0) {
			return;
		}
		if (b[i][j] == Arrow.UP_LEFT) {
			printLcs(b, first, i - 1, j - 1, output);
			output.append(first.charAt(i-1));
		} else if (b[i][j] == Arrow.UP) {
			printLcs(b, first, i - 1, j, output);
		} else {
			printLcs(b, first, i, j - 1, output);
		}

	}
}

