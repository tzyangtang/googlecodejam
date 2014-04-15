package com.tzyangtang.googlecodejam.practice.chessboards;

public class Cell {
	// 0 = black, 1 = white 
	boolean white;
	boolean taken;
	int x;
	int y;
	int maxSize;
	
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean isWhite() {
		return white;
	}
	public boolean isTaken() {
		return taken;
	}
	public Cell(int x, int y, boolean white) {
		super();
		this.x = x;
		this.y = y;
		this.white = white;
	}
	
}
