package com.tzyangtang.googlecodejam.practice.milkshakes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MilkShakes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numberOfCase;

		try {
			File file = new File(args[1]);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			numberOfCase = Integer.parseInt(br.readLine());
			for (int i = 0; i < numberOfCase; i++) {
				int numberOfFlavors = Integer.parseInt(br.readLine());
				int numberOfCustomers = Integer.parseInt(br.readLine());
				Boolean[] finalBatch = new Boolean[numberOfFlavors];
				boolean impossiable = false;
				// read all customer preferences
				List<Customer> customers = new ArrayList<Customer>();
				for (int idx = 0; idx < numberOfCustomers; idx++) {
					String rawData = br.readLine();
					String[] tokensStrings = rawData.split(" ");
					Customer customer = new Customer();
					int numOfFavs = Integer.parseInt(tokensStrings[0]);
					customer.setNumberOfFavs(numOfFavs);
					Map<Integer, Boolean> favs = new HashMap<Integer, Boolean>();
					for (int innerIdx = 0; innerIdx < numOfFavs; innerIdx++) {
						int flavor = Integer
								.parseInt(tokensStrings[innerIdx * 2 + 1]);
						boolean malted = tokensStrings[innerIdx * 2 + 2]
								.equalsIgnoreCase("1");
						favs.put(flavor, malted);
					}
					customer.setFavs(favs);
					customers.add(customer);
				}
				
				System.out.println("numberOfFlavors:" + numberOfFlavors);
				System.out.println("numberOfCustomers:" + numberOfCustomers);
				for (Customer customer : customers) {
					System.out.print(customer.getNumberOfFavs() + " ");
					Map<Integer, Boolean> favs = customer.getFavs();
					System.out.println(favs.toString());
				}

				boolean newChange;
				search: do {
					newChange = false;
					for (Customer customer : customers) {
						if (customer.isSatisfied())
							continue;
						Map<Integer, Boolean> favs = customer.getFavs();
						// if customer only has one preferred malted flavor
						if (favs.containsValue(true) && favs.size() == 1) {
							for (Integer key : favs.keySet()) {
								if (finalBatch[key - 1] == null
										|| finalBatch[key - 1] == true) {
									finalBatch[key - 1] = true;
									customer.setSatisfied(true);
									for (Customer innerCust : customers) {
										if (innerCust.isSatisfied())
											continue;
										if (innerCust.getFavs()
												.containsKey(key)) {
											if (innerCust.getFavs().get(key) == true) {
												innerCust.setSatisfied(true);
												continue;
											} else {
												innerCust.getFavs().remove(key);
												newChange = true;
											}
										}
									}
								} else {
									impossiable = true;
									break search;
								}
							}
						}
					}
				} while (newChange);

				// loop through the remaining unsatisfied customers
				for (Customer customer : customers) {
					if (customer.isSatisfied())
						continue;
					Map<Integer, Boolean> favs = customer.getFavs();
					boolean found = false;
					for (Integer key : favs.keySet()) {
						if (favs.get(key) == false
								&& (finalBatch[key - 1] == null || finalBatch[key - 1] == false)) {
							customer.setSatisfied(true);
							found = true;
						} else if (favs.get(key) == true
								&& finalBatch[key - 1] != null
								&& finalBatch[key - 1] == true) {
							customer.setSatisfied(true);
							found = true;
						}
					}
					if (!found) {
						impossiable = true;
						break;
					}
				}

				StringBuilder sb = new StringBuilder();
				if (impossiable)
					sb.append("Case #" + (i + 1) + ": IMPOSSIBLE");
				else {
					sb.append("Case #" + (i + 1) + ": "
							+ printBatch(finalBatch));
				}

				String msg = sb.toString().trim() + "\n";
				System.out.print(msg);
				bw.write(msg);

			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String printBatch(Boolean[] finalBatch) {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < finalBatch.length; i++) {
			if (finalBatch[i] != null && finalBatch[i])
				sBuilder.append("1");
			else {
				sBuilder.append("0");
			}
			sBuilder.append(" ");
		}
		return sBuilder.toString();
	}
	
	public static class Customer {
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
}
