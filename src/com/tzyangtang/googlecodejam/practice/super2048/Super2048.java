package com.tzyangtang.googlecodejam.practice.super2048;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Super2048 {

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
				String direction = scanner.next();

				int grid[][] = new int[n][n];
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						grid[j][k] = scanner.nextInt();
					}
				}

				if (direction.equals("up") || direction.equals("down")) {
					grid = rotateRight(grid);
				}

				for (int j = 0; j < n; j++) {
					if (direction.equals("right") || direction.equals("up")) {
						// right
						for (int k = n - 1; k >= 0; k--) {
							for (int m = k - 1; m >= 0; m--) {
								if (grid[j][k] == 0) {
									if (grid[j][m] != 0) {
										grid[j][k] = grid[j][m];
										grid[j][m] = 0;
										continue;
									}
								} else {
									if (grid[j][m] != 0) {
										if (grid[j][m] == grid[j][k]) {
											grid[j][k] = grid[j][k] * 2;
											grid[j][m] = 0;
											break;
										}
										break;
									}
								}
							}
						}
					} else if (direction.equals("left")
							|| direction.equals("down")) {
						// left
						for (int k = 0; k < n; k++) {
							for (int m = k + 1; m < n; m++) {
								if (grid[j][k] == 0) {
									if (grid[j][m] != 0) {
										grid[j][k] = grid[j][m];
										grid[j][m] = 0;
										continue;
									}
								} else {
									if (grid[j][m] != 0) {
										if (grid[j][m] == grid[j][k]) {
											grid[j][k] = grid[j][k] * 2;
											grid[j][m] = 0;
											break;
										}
										break;
									}
								}
							}
						}
					}
				}

				if (direction.equals("up") || direction.equals("down")) {
					grid = rotateLeft(grid);
				}

				StringBuilder sb = new StringBuilder();
				sb.append("Case #" + (i + 1) + ": ");
				String msg = sb.toString().trim() + "\n";
				System.out.print(msg);
				bw.write(msg);
				printGrid(grid, bw);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	private static int[][] rotateRight(int[][] grid) {
		int n = grid.length;
		int newGrid[][] = new int[n][n];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				newGrid[j][n - i - 1] = grid[i][j];
			}
		}
		return newGrid;
	}

	private static int[][] rotateLeft(int[][] grid) {
		int n = grid.length;
		int newGrid[][] = new int[n][n];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				newGrid[n - j - 1][i] = grid[i][j];
			}
		}
		return newGrid;
	}

	private static void printGrid(int[][] grid, BufferedWriter bw) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				System.out.print(grid[i][j]);
				System.out.print(" ");
				if (bw != null) {
					try {
						bw.write(grid[i][j] + " ");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.print("\n");
			if (bw != null) {
				try {
					bw.write("\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
