package com.tzyangtang.googlecodejam.practice.filefixit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileFixIt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numberOfCase;

		try {
			File file = new File(args[1]);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(new FileReader(args[0]));

			// String sCurrentLine;
			numberOfCase = Integer.parseInt(br.readLine());
			for (int i = 0; i < numberOfCase; i++) {
				Set<String> existingSet = new HashSet<>();
				Set<String> desiredSet = new HashSet<>();
				String tokensString = br.readLine();
				int numberOfExistingDirs = Integer.parseInt(tokensString
						.split(" ")[0]);
				int numberOfDesiredDirs = Integer.parseInt(tokensString
						.split(" ")[1]);
				for (int j = 0; j < numberOfExistingDirs; j++) {
					String existingDirString = br.readLine();
					addToExistingSet(existingSet, existingDirString);
				}
				for (int j = 0; j < numberOfDesiredDirs; j++) {
					String desiredDirString = br.readLine();
					addToDesiredSet(desiredSet, desiredDirString);
				}

				int count = 0;
				for (String item : desiredSet) {
					if (!existingSet.contains(item)) {
						count++;
					}
				}

				StringBuilder sb = new StringBuilder();
				sb.append("Case #" + (i + 1) + ": " + count);
				String msg = sb.toString().trim() + "\n";
				System.out.print(msg);
				bw.write(msg);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addToExistingSet(Set<String> existingSet,
			String existingDirString) {
		// /home/gcj/finals
		String[] tokens = existingDirString.split("/");
		StringBuilder curBuilder = new StringBuilder();
		for (String string : tokens) {
			if (string != null && !string.equals("")) {
				curBuilder.append("/");
				curBuilder.append(string);
				existingSet.add(curBuilder.toString());
			}
		}
	}

	private static void addToDesiredSet(Set<String> desiredSet,
			String desiredDirString) {
		String[] tokens = desiredDirString.split("/");
		StringBuilder curBuilder = new StringBuilder();
		for (String string : tokens) {
			if (string != null && !string.equals("")) {
				curBuilder.append("/");
				curBuilder.append(string);
				desiredSet.add(curBuilder.toString());
			}
		}
	}

}
