package com.neotechlabs.interviews;

import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;

public class NiyoSolutionOne {

	public static void main(String[] args) {
		//List<Phone> availablePhones = new ArrayList<>();
		
		TreeSet<Phone> availablePhones = new TreeSet<>();
		
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		for (int a = 0; a < N; a++) {
			String S = scanner.next();
			int A = scanner.nextInt();
			int B = scanner.nextInt();
			int C = scanner.nextInt();
			int D = scanner.nextInt();
			
			availablePhones.add(new Phone(S, A, B, C, D));			
		}
				
		int Q = scanner.nextInt();
		for (int a = 0; a < Q; a++) {
			String H = scanner.next();
			int E = scanner.nextInt();
			int F = scanner.nextInt();
			int G = scanner.nextInt();
			
			Optional<Phone> result = availablePhones.stream()					
					.filter(p -> p.getOs().equalsIgnoreCase(H))
					.filter(p -> p.getPrice() <= G)
					.filter(p -> p.getRam() == E)
					.filter(p -> p.getMemorySpace() == F)					
					.reduce((p1, p2) -> p1.getRating() >= p2.getRating() ? p1: p2);
			
			if (result.isPresent()) {
				System.out.println(result.get().getRating());
			} else {
				System.out.println(-1);
			}
		}
		
		scanner.close();
	}
	
	static class Phone implements Comparable<Phone> {
		String os;
		int ram;
		int memorySpace;
		int price;
		int rating;
		
		public Phone(String os, int ram, int memorySpace, int price, int rating) {
			this.os = os;
			this.ram = ram;
			this.memorySpace = memorySpace;
			this.price = price;
			this.rating = rating;
		}

		public String getOs() {
			return os;
		}

		public void setOs(String os) {
			this.os = os;
		}

		public int getRam() {
			return ram;
		}

		public void setRam(int ram) {
			this.ram = ram;
		}

		public int getMemorySpace() {
			return memorySpace;
		}

		public void setMemorySpace(int memorySpace) {
			this.memorySpace = memorySpace;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public int getRating() {
			return rating;
		}

		public void setRating(int rating) {
			this.rating = rating;
		}

		@Override
		public String toString() {
			return "Phone [os=" + os + ", ram=" + ram + ", memorySpace=" + memorySpace + ", price=" + price
					+ ", rating=" + rating + "]";
		}

//		@Override
//		public int compare(Phone p1, Phone p2) {
//			int result = p1.getOs().compareTo(p2.getOs());
//			if (result == 0) {
//				if (p1.getRam() == p2.getRam()) {
//					if (p1.getMemorySpace() == p2.getMemorySpace()) {
//						result = p1.getPrice() < p2.getPrice() ? -1 : 1;
//					} else {
//						result = p1.getMemorySpace() < p2.getMemorySpace() ? -1 : 1;
//					}
//				} else {
//					result = p1.getRam() < p2.getRam() ? -1 : 1;
//				}
//			}
//			return result;
//		}

		@Override
		public int compareTo(Phone p2) {
			int result = 0;
			if (p2 != null) {
				result = this.getOs().compareTo(p2.getOs());
				if (result == 0) {
					if (this.getRam() == p2.getRam()) {
						if (this.getMemorySpace() == p2.getMemorySpace()) {
							result = this.getPrice() < p2.getPrice() ? -1 : 1;
						} else {
							result = this.getMemorySpace() < p2.getMemorySpace() ? -1 : 1;
						}
					} else {
						result = this.getRam() < p2.getRam() ? -1 : 1;
					}
				}
			}

			return result;
		}
	}
}
