package movieTitlesMain;

public class Movie  {
	 String title;
	 String releaseYear;
	 public Movie left;
	 public Movie right;
	 
	 //no args constructor
	 public Movie(){

	 }
	 
	 public Movie(String title, String releaseYear){
		 this.title = title;
	     this.releaseYear = releaseYear;
	 }
 
	 public String toString(){
	     return title + " " + releaseYear;
	 }	 
}
