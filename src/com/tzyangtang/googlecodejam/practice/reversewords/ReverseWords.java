package com.tzyangtang.googlecodejam.practice.reversewords;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReverseWords {

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
				String sentences = br.readLine();
				String[] words = sentences.split(" ");
				StringBuilder sb = new StringBuilder();
				sb.append("Case #" + (i + 1) + ": ");
				for (int j = words.length - 1; j >= 0; j--) {
					sb.append(words[j] + " ");
				}
				String msg = sb.toString().trim() + "\n";
				System.out.print(msg);
				bw.write(msg);

//				int credit = Integer.parseInt(br.readLine());
//				int numberOfItems = Integer.parseInt(br.readLine());
//				String itemsStr = br.readLine();
//				String[] itemStrings = itemsStr.split(" ");
//				Integer[] items = new Integer[numberOfItems];
//				for (int j = 0; j < itemStrings.length; j++) {
//					items[j] = Integer.parseInt(itemStrings[j]);
//				}
//
//				// solve problem here...
//				for (int idx1 = 0; idx1 < items.length; idx1++) {
//					for (int idx2 = idx1 + 1; idx2 < items.length; idx2++) {
//						if ((items[idx1] + items[idx2]) == credit) {
//							String msg = "Case #" + (i + 1) + ": " + (idx1 + 1)
//									+ " " + (idx2 + 1);
//							System.out.println(msg);
//							bw.write(msg + "\n");
//							break;
//						}
//					}
//				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
