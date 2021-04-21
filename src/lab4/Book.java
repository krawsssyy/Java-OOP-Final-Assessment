package lab4;

public class Book { // clasa ce reprzinta o carte
	private int id; // idul cartii
	private String title; // titlul cartii
	private Author author; // autorul acelei carti
	private int releaseYear; // anul aparitiei
	private float price; // pretul cartii
	private Publisher publisher; // editura de care apartine
	private String quota; // cota cartii(construita in service)
	
	public Book () { // constructor implicit
		this.id = 0;
		this.title = new String();
		this.author = new Author();
		this.releaseYear = 0;
		this.price = 0.0f;
		this.publisher = new Publisher();
		this.quota = new String();
	}
	
	public Book(int id, String title, String authName, int releaseYear, float price, String publName, String quota) {
		// constructor explicit
		this.id = id;
		this.title = title;
		this.author = new Author(authName);
		this.releaseYear = releaseYear;
		this.price = price;
		this.publisher = new Publisher(publName);
		this.quota = quota;
	}
	
	public int getID() { // getter pentru idul cartii
		return this.id;
	}
	
	public String getTitle() { // getter pentru titul cartii
		return this.title;
	}
	
	public String getAuthor() { // getter pentru numele autorului
		return this.author.getAuthorName();
	}
	
	public int getReleaseYear() { // getter pentru anul publicatiei
		return this.releaseYear;
	}
	
	public float getPrice() { // getter pentru pretul cartii
		return this.price;
	}
	
	public String getPublisher() { // getter pentru numele editurii de care apartine cartea
		return this.publisher.getPublisherName();
	}
	
	public String getQuota() { // getter pentru cota cartii
		return this.quota;
	}
	
	
}
