package movieTitlesMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class movieTitlesMain {

	public static void main(String[] args) throws Exception {
		
		int rows = 9742;
		int cols = 3;
		int index = 0;
		
		String [][] dataArray = new String [rows][cols];
		String delimiter = "\t";
		String regex = "([0-9]+)";
		Pattern pattern = Pattern.compile(regex);
		String [] releaseYear = new String [9742];
		String [] movieTitles = new String [9742];
		
		Scanner sc = new Scanner(new File("src/movies.tsv"));
		
		PrintStream output = new PrintStream(new FileOutputStream(new File("src/Movie-Output.txt")));
		
		//reads data in from tsv file and puts it into 2D array
		readData(dataArray, sc, index, delimiter, cols);
		
		//seperates data into two arrays for release year and movie title
		seperateData(dataArray, releaseYear, movieTitles, pattern);
		
		//creates a binary search tree
		MovieBST tree = new MovieBST();
		
		//adds data into binary search tree
		addDataToBST(movieTitles, releaseYear, tree);
		
		
		printData(output, tree);
	}
	
	public static void readData(String [][] dataArray, Scanner sc, int index, String delimiter, int cols) {
		 while (sc.hasNextLine()) {
				String [] temp = sc.nextLine().split(delimiter);
				for (int c = 0; c<cols; c++) {
					dataArray[index][c] = temp [c];
				}
				index++;
			}
			sc.close(); 
	 }
	
	public static void addDataToBST(String movieTitles[], String releaseYear[], MovieBST tree) {
		for(int i = 0; i<9742;i++) {
			tree.addNode(movieTitles[i], releaseYear[i]);
		}
	}
	
	public static void seperateData (String dataArray[][], String releaseYear[], String movieTitles[], Pattern pattern) {
		for (int i =0; i<dataArray.length; i++) {
			Matcher matcher = pattern.matcher(dataArray[i][1]);
			while(matcher.find()) {
				releaseYear [i] = matcher.group();
				
			}
		}
		
		for (int i =0; i<dataArray.length; i++) {
			String title = dataArray[i][1];
			int iend = title.indexOf("(");
				if (iend != -1) 
				{
					movieTitles [i]= title.substring(0 , iend); 
				}
		}
		
	}
	
	public static void printData(PrintStream output, MovieBST tree) throws FileNotFoundException {
		output.println("Movies between Bug's Life & Harry Potter: \n");
		MovieBST.printSubset(tree.root, "Bug's Life", "Harry Potter", output);
		output.println("");
		output.println("Movies between Back to the Future & The Hulk: \n");
		MovieBST.printSubset(tree.root, "Back to the Future", "Hulk", output);
		output.println("");
		output.println("Movies between Toy Story & WALL-E: \n");
		MovieBST.printSubset(tree.root, "Toy Story", "WALL_E", output);
	}
	

}
