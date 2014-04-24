package com.tzyangtang.googlecodejam.practice.rotate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Rotate {

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

				char grid[][] = new char[n][n];
				for (int y = 0; y < n; y++) {
					String line = scanner.next();
					for (int x = 0; x < n; x++) {
						grid[y][x] = line.charAt(x);
					}
				}

				grid = rotate(grid, n);
				Set<String> red = new HashSet<>();
				Set<String> blue = new HashSet<>();
				for (int y = 0; y < n; y++) {
					for (int x = 0; x < n; x++) {
						if (grid[y][x] == 'R') {
							red.add(x + "-" + y);
						} else if (grid[y][x] == 'B') {
							blue.add(x + "-" + y);
						}
					}
				}

				boolean redPass = false;
				boolean bluePass = false;
				for (int y = 0; y < n; y++) {
					for (int x = 0; x < n; x++) {
						// check horizontal
						int redCount = 0;
						int blueCount = 0;
						for (int z = 0; z < k; z++) {
							if (red.contains((x + z) + "-" + y)) {
								redCount++;
							}
							if (blue.contains((x + z) + "-" + y)) {
								blueCount++;
							}
						}
						if (redCount == k)
							redPass = true;
						if (blueCount == k)
							bluePass = true;
						// check vertical
						redCount = 0;
						blueCount = 0;
						for (int z = 0; z < k; z++) {
							if (red.contains(x + "-" + (y + z))) {
								redCount++;
							}
							if (blue.contains(x + "-" + (y + z))) {
								blueCount++;
							}
						}
						if (redCount == k)
							redPass = true;
						if (blueCount == k)
							bluePass = true;

						// check diagonal forward
						redCount = 0;
						blueCount = 0;
						for (int z = 0; z < k; z++) {
							if (red.contains((x - z) + "-" + (y + z))) {
								redCount++;
							}
							if (blue.contains((x - z) + "-" + (y + z))) {
								blueCount++;
							}
						}
						if (redCount == k)
							redPass = true;
						if (blueCount == k)
							bluePass = true;

						// check diagonal backward
						redCount = 0;
						blueCount = 0;
						for (int z = 0; z < k; z++) {
							if (red.contains((x + z) + "-" + (y + z))) {
								redCount++;
							}
							if (blue.contains((x + z) + "-" + (y + z))) {
								blueCount++;
							}
						}
						if (redCount == k)
							redPass = true;
						if (blueCount == k)
							bluePass = true;
					}
				}

				String result = null;
				if (redPass && bluePass) {
					result = "Both";
				} else if (redPass) {
					result = "Red";
				} else if (bluePass) {
					result = "Blue";
				} else {
					result = "Neither";
				}
				StringBuilder sb = new StringBuilder();
				sb.append("Case #" + (i + 1) + ": " + result);
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

	private static char[][] rotate(char[][] grid, int n) {
		// gravity right
		char[][] newGrid = new char[n][n];
		for (int i = 0; i < n; i++) {
			char[] newLine = new char[n];
			int rightIdx = n - 1;
			int leftIdx = 0;
			for (int j = n - 1; j >= 0; j--) {
				if (grid[i][j] == '.') {
					newLine[leftIdx++] = grid[i][j];
				} else {
					newLine[rightIdx--] = grid[i][j];
				}
			}
			newGrid[i] = newLine;
		}
		// rotate
		char[][] finalGrid = new char[n][n];
		for (int y = 0; y < n; y++) {
			finalGrid[y] = new char[n];
		}

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				finalGrid[y][x] = newGrid[n - x - 1][y];
			}
		}

		return finalGrid;
	}
}
