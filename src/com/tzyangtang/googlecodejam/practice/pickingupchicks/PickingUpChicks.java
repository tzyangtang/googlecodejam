package com.tzyangtang.googlecodejam.practice.pickingupchicks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class PickingUpChicks {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numberOfCase;
		Scanner scanner = null;
		FileWriter fw;
		BufferedWriter bw;

		try {
			File file = new File(args[1]);
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			scanner = new Scanner(new File(args[0]));
			numberOfCase = scanner.nextInt();
			for (int i = 0; i < numberOfCase; i++) {
				int n = scanner.nextInt();
				int k = scanner.nextInt();
				int b = scanner.nextInt();
				int t = scanner.nextInt();
				int x[] = new int[n];
				for (int j = 0; j < n; j++)
					x[j] = scanner.nextInt();
				int v[] = new int[n];
				for (int j = 0; j < n; j++)
					v[j] = scanner.nextInt();
//				Chick[] chicks = new Chick[n];
//				for (int j = 0; j < n; j++)
//					chicks[j] = new Chick(x[j], v[j], t);
				// Arrays.sort(chicks, new FinalPosCompare());
				int count = 0;
				int skipped = 0;
				int swapped = 0;
				for (int j = n - 1; j >= 0; j--) {
//					if(chicks[j].getFinalPos() >= b) {
					if ((x[j] + (v[j] * t)) >= b) {
							count++;
							swapped = swapped + skipped;
							if (count == k)
								break;
						} else {
							skipped++;
						}
				}

				StringBuilder sb = new StringBuilder();
				sb.append("Case #" + (i + 1) + ": "
						+ ((count < k) ? "IMPOSSIBLE" : swapped));
				String msg = sb.toString().trim() + "\n";
				System.out.print(msg);
				bw.write(msg);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

//	private static class Chick {
//		private int finalPos;
//
//		public Chick(int location, int speed, int targetTime) {
//			super();
//			finalPos = location + (speed * targetTime);
//		}
//
//		public int getFinalPos() {
//			return finalPos;
//		}
//	}
//
//	private static class FinalPosCompare implements Comparator<Chick> {
//		public int compare(Chick c1, Chick c2) {
//			return c1.getFinalPos() - c2.getFinalPos();
//		}
//	}
}
