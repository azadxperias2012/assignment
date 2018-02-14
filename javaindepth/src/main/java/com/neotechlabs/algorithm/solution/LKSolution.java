package com.neotechlabs.algorithm.solution;

public class LKSolution {
	
	Node head;
	
	class Node {
		private int data;
		private Node next;

		public Node(int data) {
			this.setData(data);
			this.setNext(null);
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}
	}
	
	void push(int data) {
		if (head == null) {
			head = new Node(data);
			return;
		}
		
		Node x = head;
		while (true) {
			if (x.getNext() == null) {
				x.setNext(new Node(data));
				break;
			}
			x = x.getNext();			
		}
    }
	
	void printList() {
		StringBuilder sb = new StringBuilder();
		Node x = head;
		while (true) {
			if (x == null) {
				sb.append("NULL");
				break;
			}
			sb.append(x.getData());
			sb.append(" -> ");
			x = x.getNext();
		}
		System.out.println(sb.toString());
	}
	
	void printList(Node p) {
		StringBuilder sb = new StringBuilder();
		Node x = p;
		while (true) {
			if (x == null) {
				sb.append("NULL");
				break;
			}
			sb.append(x.getData());
			sb.append(" -> ");
			x = x.getNext();
		}
		System.out.println(sb.toString());
	}
	
	void alterListByEvenAndOdd() {
		
		Node evenStart = null;
		Node evenEnd = null;
		
		Node oddStart = null;
		Node oddEnd = null;
		
		Node aux = head;
		
		while (aux != null) {
			
			if (aux.getData() % 2 == 0) {
				if (evenStart == null) {
					evenStart = aux;
					evenEnd = evenStart;
				} else {
					evenEnd.setNext(aux);
					evenEnd = evenEnd.getNext();
				}
			} else {
				if (oddStart == null) {
					oddStart = aux;
					oddEnd = oddStart;
				} else {
					oddEnd.setNext(aux);
					oddEnd = oddEnd.getNext();
				}
			}
			
			aux = aux.getNext();
		}
		
		evenEnd.setNext(oddStart);
		
		head = evenStart;
	}

	public static void main(String[] args) {
		LKSolution sol = new LKSolution();
		
		sol.push(1);
		sol.push(5);
		sol.push(2);				
		sol.push(3);
		sol.push(7);
		
		System.out.println("Input list: ");
		sol.printList();
		sol.alterListByEvenAndOdd();
		System.out.println("alterListByEvenAndOdd: ");
		sol.printList();
	}

}
