package lab4;

public class Author { // clasa ce reprezinta autorul unei carti
	private String name; // atribut pentru numele autorului
	
	public Author() { // constructor implicit
		this.name = new String();
	}
	
	public Author(String name){ // constructor explicit
		this.name = name;
	}
	
	public String getAuthorName() { // getter pentru numele autorului
		return this.name;
	}

}
