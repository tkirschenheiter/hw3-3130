package movieTitlesMain;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class MovieBST {
	 Movie root;
	 
	 public void addNode(String title, String releaseYear) {
		 Movie newNode = new Movie(title, releaseYear);
		 
		 if(root == null) {
			 root = newNode;
			 
		 } else {
			 Movie focusNode = root;	 
			 Movie parent;
			 
			 while(true) {
				 parent = focusNode;
				 
				 if (title.compareToIgnoreCase(focusNode.title) < 0) {
					 focusNode = focusNode.left;
					 
					 if(focusNode == null) {
						 parent.left = newNode;
						 return;
					 }
			 
				 } else {
					 focusNode = focusNode.right;
					 
					 if (focusNode == null) {
						 parent.right = newNode;
						 return;
					 }
				   }
			 }
		   }
	 }
	 
	 public static void inOrderTraverseTree(Movie focusNode) {
		 
		 if(focusNode != null) {
			 inOrderTraverseTree(focusNode.left);
			 System.out.println(focusNode);
			 inOrderTraverseTree(focusNode.right);
		 }
	 }
	 
	 public static void printSubset(Movie root, String title1, String title2,  PrintStream output) throws FileNotFoundException {
	
		 if (root == null) {
			 return;
		 }
		 
		 if (title1.compareToIgnoreCase(root.title) < 0) {
			 printSubset(root.left, title1, title2, output); 
		 }
		 
		 if (title1.compareToIgnoreCase(root.title) <= 0 && title2.compareToIgnoreCase(root.title)>= 0) {
			 output.println(root.title);
		 }
		
		 if (title2.compareToIgnoreCase(root.title) > 0) {
			 printSubset(root.right, title1, title2, output);
		 }
		 
		
	 }
}
