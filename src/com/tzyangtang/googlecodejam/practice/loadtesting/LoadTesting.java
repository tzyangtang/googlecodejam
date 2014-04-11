package com.tzyangtang.googlecodejam.practice.loadtesting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class LoadTesting {

	// static HashMap<String, Integer> pastResults = new HashMap<>();

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

			int numberOfCases = Integer.parseInt(br.readLine());
			for (int i = 0; i < numberOfCases; i++) {
				String itemsStr = br.readLine();
				String[] itemStrings = itemsStr.split(" ");
				int l = Integer.parseInt(itemStrings[0]);
				int p = Integer.parseInt(itemStrings[1]);
				int c = Integer.parseInt(itemStrings[2]);
				
				int numberOfTimes = solve(l, p, c);

				String msg = "Case #" + (i + 1) + ": " + numberOfTimes + "\n";
				bw.write(msg);
				System.out.print(msg);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int solve(int l, int p, int c) {
		
		int numberOfTimes = 0;
		while(true)
		{
			 // L*C^(2^numberOfTimes)>=P
			if(l* Math.pow(c, Math.pow(2,numberOfTimes)) >= p)
			{
				return numberOfTimes;
			}
			else {
				numberOfTimes++;
			}
		}
	}
}
