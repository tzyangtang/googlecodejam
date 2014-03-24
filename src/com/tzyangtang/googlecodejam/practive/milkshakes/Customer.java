package com.tzyangtang.googlecodejam.practive.milkshakes;

import java.util.Map;

public class Customer {
	Integer numberOfFavs;
	Map<Integer, Boolean> favs;
	boolean satisfied;

	public Integer getNumberOfFavs() {
		return numberOfFavs;
	}

	public void setNumberOfFavs(Integer numberOfFavs) {
		this.numberOfFavs = numberOfFavs;
	}

	public Map<Integer, Boolean> getFavs() {
		return favs;
	}

	public void setFavs(Map<Integer, Boolean> favs) {
		this.favs = favs;
	}

	public boolean isSatisfied() {
		return satisfied;
	}

	public void setSatisfied(boolean satisfied) {
		this.satisfied = satisfied;
	}
}
