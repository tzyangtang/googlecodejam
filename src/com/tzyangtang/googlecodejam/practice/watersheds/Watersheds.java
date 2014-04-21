package com.tzyangtang.googlecodejam.practice.watersheds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class Watersheds {

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
				String tokenString = br.readLine();
				String[] tokens = tokenString.split(" ");
				int height = Integer.parseInt(tokens[0]);
				int width = Integer.parseInt(tokens[1]);
				Altitude[][] altitudes = new Altitude[height][width];
				for (int y = 0; y < height; y++) {
					String tmp = br.readLine();
					String[] tmpTokens = tmp.split(" ");
					for (int x = 0; x < width; x++) {
						altitudes[y][x] = new Altitude(x, y,
								Integer.parseInt(tmpTokens[x]));
					}
				}

				HashSet<Altitude> sinks = new HashSet<>();
				int label = 0;
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						Altitude self = altitudes[y][x];
						Altitude sink = findTheSink(self, altitudes, width,
								height);
						if (!sinks.contains(sink)) {
							sink.setLabel(label);
							self.setLabel(label);
							sinks.add(sink);
							label++;
						} else {
							self.setLabel(sink.getLabel());
						}
					}
				}

				String msg = "Case #" + (i + 1) + ":\n";
				bw.write(msg);
				System.out.print(msg);
				msg = printAltitudes(altitudes);
				bw.write(msg);
				System.out.print(msg);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Altitude findTheSink(Altitude self, Altitude[][] altitudes,
			int width, int height) {
		Altitude nextAltitude;
		Altitude outOfBound = new Altitude(-1, -1, 999999);
		int x = self.getX();
		int y = self.getY();
		Altitude north = ((y > 0) ? altitudes[y - 1][x] : outOfBound);
		Altitude west = ((x > 0) ? altitudes[y][x - 1] : outOfBound);
		Altitude east = ((x < (width - 1)) ? altitudes[y][x + 1] : outOfBound);
		Altitude south = ((y < (height - 1)) ? altitudes[y + 1][x] : outOfBound);
		if (self.getAltitude() <= north.getAltitude()
				&& self.getAltitude() <= west.getAltitude()
				&& self.getAltitude() <= east.getAltitude()
				&& self.getAltitude() <= south.getAltitude()) {
			return self;
		}
		// north is the smallest
		else if (north.getAltitude() <= west.getAltitude()
				&& north.getAltitude() <= east.getAltitude()
				&& north.getAltitude() <= south.getAltitude()) {
			nextAltitude = north;
		}
		// west is the smallest
		else if (west.getAltitude() <= east.getAltitude()
				&& west.getAltitude() <= south.getAltitude()) {
			nextAltitude = west;
		}
		// east is the smallest
		else if (east.getAltitude() <= south.getAltitude()) {
			nextAltitude = east;
		}
		// south is the smallest
		else {
			nextAltitude = south;
		}
		return findTheSink(nextAltitude, altitudes, width, height);
	}

	private static String printAltitudes(Altitude[][] altitudes) {
		StringBuilder sBuilder = new StringBuilder();
		for (int x = 0; x < altitudes.length; x++) {
			for (int y = 0; y < altitudes[x].length; y++) {
				sBuilder.append((char) (altitudes[x][y].getLabel() + 97));
				sBuilder.append(" ");
			}
			sBuilder.append("\n");
		}
		return sBuilder.toString();
	}

	public static class Altitude {
		private int x;
		private int y;
		private int altitude;
		private int label;

		public Altitude(int x, int y, int altitude) {
			super();
			this.x = x;
			this.y = y;
			this.altitude = altitude;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getAltitude() {
			return altitude;
		}

		public int getLabel() {
			return label;
		}

		public void setLabel(int label) {
			this.label = label;
		}

	}

}
