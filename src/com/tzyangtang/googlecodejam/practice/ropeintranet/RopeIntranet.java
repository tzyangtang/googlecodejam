package com.tzyangtang.googlecodejam.practice.ropeintranet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class RopeIntranet {

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

				int numberOfRopes = Integer.parseInt(br.readLine());
				int[][] ropes = new int[numberOfRopes][2];
				for (int j = 0; j < numberOfRopes; j++) {
					String itemsStr = br.readLine();
					String[] itemStrings = itemsStr.split(" ");
					ropes[j][0] = Integer.parseInt(itemStrings[0]);
					ropes[j][1] = Integer.parseInt(itemStrings[1]);
				}

				int outputCount = solve(ropes, numberOfRopes);

				String msg = "Case #" + (i + 1) + ": " + outputCount + "\n";
				bw.write(msg);
				System.out.print(msg);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int solve(int[][] ropes, int numberOfRopes) {
		
		int count = 0;
		for(int i=0; i < numberOfRopes; i++)
		{
			int[] current = theNSmallest(ropes, i);
			for(int j = i; j < numberOfRopes; j++)
			{
				int[] temp = theNSmallest(ropes, j);
				if(temp[1] < current[1])
					count++;
			}
		}
		
		return count;
	}
	
	public static int[] theNSmallest(int[][] ropes, int index)
	{
		int[] a = new int[ropes.length];
		for(int i = 0; i < ropes.length; i++)
		{
			a[i] = ropes[i][0];
		}
		
		Arrays.sort(a);
		for(int i = 0; i < ropes.length; i++)
		{
			if(a[index] == ropes[i][0])
				return ropes[i];
		}
		return null;
	}
}
