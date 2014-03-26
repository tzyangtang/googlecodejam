package com.tzyangtang.googlecodejam.practice.watersheds;

public class Altitude {
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
