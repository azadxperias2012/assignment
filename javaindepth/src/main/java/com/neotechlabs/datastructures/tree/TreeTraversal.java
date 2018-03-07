package com.neotechlabs.datastructures.tree;

public class TreeTraversal {
	
	private static class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int data) {
			this.data = data;
			left = right = null;
		}
	}
	
	public void inOrder(TreeNode root) {
		if (root == null)
			return;
		inOrder(root.left);
		visit(root);
		inOrder(root.right);
	}
	
	public void preOrder(TreeNode root) {
		if (root == null)
			return;
		visit(root);
		preOrder(root.left);		
		preOrder(root.right);
	}
	
	public void postOrder(TreeNode root) {
		if (root == null)
			return;		
		postOrder(root.left);		
		postOrder(root.right);
		visit(root);
	}
	
	public void visit(TreeNode node) {
		System.out.print(node.data + " ");
	}
	
	public static void main(String[] args) {
		TreeNode root = createBinarySearchTree();
		TreeTraversal traverse = new TreeTraversal();
		traverse.inOrder(root);
		System.out.println();
		traverse.preOrder(root);
		System.out.println();
		traverse.postOrder(root);
		System.out.println();
	}

	private static TreeNode createBinarySearchTree() {
		TreeNode root = new TreeNode(10);
		
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(9);
		root.left.right = new TreeNode(18);
		
		root.right = new TreeNode(20);		
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(7);

		return root;
	}

}
