package com.tzyangtang.googlecodejam.practice.chessboards;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ChessBoards {

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
				int height = Integer.parseInt(itemStrings[0]);
				int width = Integer.parseInt(itemStrings[1]);
				Cell[][] gird = new Cell[height][width];
				for (int j = 0; j < height; j++) {
					String tmpStr = br.readLine();
					for (int k = 0; k < tmpStr.length(); k++) {
						int value = Integer.parseInt(
								String.valueOf(tmpStr.charAt(k)), 16);
						gird[j][k * 4] = new Cell(j, k * 4,
								((value & 0x0008) != 0));
						gird[j][k * 4 + 1] = new Cell(j, k * 4,
								((value & 0x0004) != 0));
						gird[j][k * 4 + 2] = new Cell(j, k * 4,
								((value & 0x0002) != 0));
						gird[j][k * 4 + 3] = new Cell(j, k * 4,
								((value & 0x0001) != 0));
					}
				}

				printGird(gird, width, height);

				int[] results = new int[width];

				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						findTheMaxBoard(gird, x, y, width, height);
					}
				}

				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						findTheLargestBoard(gird, x, y, width, height, results);
					}
				}

				printResult(results);

				int count = 0;
				for (int x = 1; x < width; x++) {
					if (results[x] != 0)
						count++;
				}

				String msg = "Case #" + (i + 1) + ": " + count + "\n";
				bw.write(msg);
				System.out.print(msg);

				for (int x = width - 1; x > 0; x--) {
					if (results[x] != 0) {
						msg = x + " " + results[x] + "\n";
						bw.write(msg);
						System.out.print(msg);
					}
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printResult(int[] results) {
		for (int x = 0; x < results.length; x++) {
			System.out.print(results[x] + " ");
		}
		System.out.print("\n");
	}

	private static void findTheMaxBoard(Cell[][] gird, int originX,
			int originY, int width, int height) {
		System.out.println("--- originX:" + originX + ",originY:" + originY
				+ "---");
		int size = 0;
		int passedSize = 0;
		Queue<Cell> candidates = new LinkedList<>();
		top: while (true) {
			Queue<Cell> newCandidates = new LinkedList<>();
			size++;
			System.out.print("Testing size:" + size);
			if ((originX + size) > width || (originY + size) > height)
				break top;
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					// first column except first row
					if (y > 0 && x == 0) {
						// check upper cell
						if (gird[originY + y][originX + x].isWhite() == gird[originY
								+ y - 1][originX + x].isWhite()) {
							System.out.println(" location x:" + (originX + x)
									+ ",y:" + (originY + y) + "failed test 2");
							break top;
						}
					}
					//
					if (x > 0) {
						// check left cell
						if (gird[originY + y][originX + x].isWhite() == gird[originY
								+ y][originX + x - 1].isWhite()) {
							System.out.println(" location x:" + (originX + x)
									+ ",y:" + (originY + y) + "failed test 3");
							break top;
						}
					}
					newCandidates.add(gird[originY + y][originX + x]);
					// System.out.println("location x:" + (originX + x) + ",y:"
					// + (originY + y) + "passed test");
				}
			}
			System.out.println(" passed");
			passedSize = size;
			candidates = newCandidates;
		}

		for (Cell cell : candidates) {
			if (passedSize > cell.getMaxSize())
				cell.setMaxSize(passedSize);
		}
	}

	private static void findTheLargestBoard(Cell[][] gird, int originX,
			int originY, int width, int height, int[] results) {
		System.out.println("--- originX:" + originX + ",originY:" + originY
				+ "---");
		int size = 0;
		int passedSize = 0;
		Queue<Cell> candidates = new LinkedList<>();
		top: while (true) {
			Queue<Cell> newCandidates = new LinkedList<>();
			size++;
			System.out.print("Testing size:" + size);
			if ((originX + size) > width || (originY + size) > height)
				break top;
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					if (gird[originY + y][originX + x].getMaxSize() > gird[originY][originX]
							.getMaxSize()) {
						System.out.println(" location x:" + (originX + x)
								+ ",y:" + (originY + y) + "failed test 0");
						break top;
					}
					if (gird[originY + y][originX + x].isTaken()) {
						System.out.println(" location x:" + (originX + x)
								+ ",y:" + (originY + y) + "failed test 1");
						break top;
					}
					// first column except first row
					if (y > 0 && x == 0) {
						// check upper cell
						if (gird[originY + y][originX + x].isWhite() == gird[originY
								+ y - 1][originX + x].isWhite()) {
							System.out.println(" location x:" + (originX + x)
									+ ",y:" + (originY + y) + "failed test 2");
							break top;
						}
					}
					//
					if (x > 0) {
						// check left cell
						if (gird[originY + y][originX + x].isWhite() == gird[originY
								+ y][originX + x - 1].isWhite()) {
							System.out.println(" location x:" + (originX + x)
									+ ",y:" + (originY + y) + "failed test 3");
							break top;
						}
					}
					newCandidates.add(gird[originY + y][originX + x]);
					// System.out.println("location x:" + (originX + x) + ",y:"
					// + (originY + y) + "passed test");
				}
			}
			System.out.println(" passed");
			passedSize = size;
			candidates = newCandidates;
		}

		for (Cell cell : candidates)
			cell.setTaken(true);
		results[passedSize]++;
	}

	private static void printGird(Cell[][] gird, int width, int height) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print((gird[i][j].isWhite()) ? "1 " : "0 ");
			}
			System.out.print("\n");
		}
	}

}
