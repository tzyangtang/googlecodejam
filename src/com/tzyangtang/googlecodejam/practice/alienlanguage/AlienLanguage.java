package com.tzyangtang.googlecodejam.practice.alienlanguage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AlienLanguage {

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

			String firstLine = br.readLine();
			String[] tokens = firstLine.split(" ");
			int numberOfCharacters = Integer.parseInt(tokens[0]);
			int numberOfWordsInDictionary = Integer.parseInt(tokens[1]);
			int numberOfPatterns = Integer.parseInt(tokens[2]);
			String[] words = new String[numberOfWordsInDictionary];
			for (int i = 0; i < numberOfWordsInDictionary; i++) {
				words[i] = br.readLine();
			}

			for (int i = 0; i < numberOfPatterns; i++) {
				List<HashSet<Character>> patternSets = new ArrayList<HashSet<Character>>();
				String patternString = br.readLine();
				String remainString = patternString;
				while (remainString.length() > 0) {
					if (remainString.indexOf("(") == -1
							|| remainString.indexOf("(") > 0) {
						HashSet<Character> localHashSet = new HashSet<>();
						localHashSet.add(remainString.charAt(0));
						remainString = remainString.substring(1);
						patternSets.add(localHashSet);
					} else {
						HashSet<Character> localHashSet = new HashSet<>();
						String sub = remainString.substring(1,
								remainString.indexOf(")"));
						for (int idx = 0; idx < sub.length(); idx++) {
							localHashSet.add(sub.charAt(idx));
						}
						remainString = remainString.substring(remainString
								.indexOf(")") + 1);
						patternSets.add(localHashSet);
					}
					System.out.println(remainString);
				}

				int counter = 0;
				for (int idx = 0; idx < numberOfWordsInDictionary; idx++) {
					boolean match = true;
					for (int j = 0; j < numberOfCharacters; j++) {
						if (!patternSets.get(j).contains(words[idx].charAt(j))) {
							match = false;
							break;
						}
					}
					if (match)
						counter++;
				}

				String msg = "Case #" + (i + 1) + ": " + counter;
				System.out.println(msg);
				bw.write(msg + "\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
