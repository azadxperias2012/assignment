package com.neotechlabs.algorithm.solution;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

public class EEInterview {
	
	static class Graph {
		int vertices;
		LinkedList<Integer> adjacencyListArr[];
		int[] values;
		int pos = 1;
		
		Graph(int vertices) {
			this.vertices = vertices + 1;
			adjacencyListArr = new LinkedList[this.vertices];
			values = new int[this.vertices];
			
			for (int v = 1; v < this.vertices; v++) {
				adjacencyListArr[v] = new LinkedList<>();
			}
		}
		
		public void addValue(int value) {
			values[pos++] = value;
		}
		
		// undirected graph
		public void addEdge(int source, int destination) {
			adjacencyListArr[source].add(destination);
			adjacencyListArr[destination].add(source);
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int Q = scanner.nextInt();
		
		Graph unDirectedGraph = new Graph(N);
		for (int i = 0; i < N; i++) {
			unDirectedGraph.addValue(scanner.nextInt());
		}
		
		for (int i = 0; i < N - 1; i++) {
			int U = scanner.nextInt();
			int V = scanner.nextInt();
			
			unDirectedGraph.addEdge(U, V);
		}

		while (Q-- > 0) {
			int X = scanner.nextInt();
			int K = scanner.nextInt();
		}
		
		System.out.println("-1");
		scanner.close();
	}
	
	private static void sol3() {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int k = scanner.nextInt();
		
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = scanner.nextInt();
		}
		
		System.out.println("-1");
		scanner.close();
	}
	
	private static void sol1() {
		Scanner scanner = new Scanner(System.in);
		TreeSet<Integer> ascTreeSet = new TreeSet<>();
		TreeSet<Integer> descTreeSet = new TreeSet<>(
		new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int compareTo = o1.compareTo(o2);
				return (compareTo * (-1));
			};
		});
		int maxValue = 1;

		int N = scanner.nextInt();
		while (N-- > 0) {
			int input = scanner.nextInt();
			if (input > maxValue) {
				maxValue = input;
			}

			if (input == maxValue) {
				ascTreeSet.add(input);
			} else {
				descTreeSet.add(input);
			}
        }
		
		int Q = scanner.nextInt();
		while (Q-- > 0) {
			int val = scanner.nextInt();
			if (val != maxValue) {
				if (!ascTreeSet.contains(val)) {
					ascTreeSet.add(val);					
				} else if (!descTreeSet.contains(val)) {
					descTreeSet.add(val);
				}
			}
			int size = ascTreeSet.size() + descTreeSet.size();
			System.out.println(size);
		}
		
		int size = ascTreeSet.size() + descTreeSet.size();
		if (size > 0) {
			for (Integer res : ascTreeSet) {
				System.out.print(res + " ");
			}
			for (Integer res : descTreeSet) {
				System.out.print(res + " ");
			}
			System.out.println("");
		}
		
        scanner.close();
	}

}
