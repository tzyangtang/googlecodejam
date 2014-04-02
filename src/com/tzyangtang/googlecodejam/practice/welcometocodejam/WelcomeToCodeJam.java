package com.tzyangtang.googlecodejam.practice.welcometocodejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WelcomeToCodeJam {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			File file = new File(args[1]);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(new FileReader(args[0]));

			String welcomeToCodeJam = "welcome to code jam";
			int numberOfCases = Integer.parseInt(br.readLine());
			for (int i = 0; i < numberOfCases; i++) {
				String testString = br.readLine();

				int count = howManyTimesItAppear(testString, welcomeToCodeJam);
				String outputCount = String.format("%04d", count);

				String msg = "Case #" + (i + 1) + ": " + outputCount + "\n";
				bw.write(msg);
				System.out.print(msg);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int howManyTimesItAppear(String testString,
			String welcomeToCodeJam) {

		if (testString.length() == 0 || welcomeToCodeJam.length() == 0) {
			return 0;
		}

		if (welcomeToCodeJam.length() == 1) {
			int subCount = 0;
			for (int i = 0; i < testString.length(); i++) {
				if (welcomeToCodeJam.charAt(0) == testString.charAt(i))
					subCount++;
			}
			return subCount;
		}

		if (testString.length() == 1) {
			if (welcomeToCodeJam.length() == 1) {
				if (testString.charAt(0) == welcomeToCodeJam.charAt(0)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}

		if (testString.charAt(testString.length() - 1) == welcomeToCodeJam
				.charAt(welcomeToCodeJam.length() - 1)) {
			return howManyTimesItAppear(
					testString.substring(0, testString.length() - 1),
					welcomeToCodeJam)
					+ howManyTimesItAppear(
							testString.substring(0, testString.length() - 1),
							welcomeToCodeJam.substring(0,
									welcomeToCodeJam.length() - 1));
		} else {
			return howManyTimesItAppear(
					testString.substring(0, testString.length() - 1),
					welcomeToCodeJam);
		}
	}
}
