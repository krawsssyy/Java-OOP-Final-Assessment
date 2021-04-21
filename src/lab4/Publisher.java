package lab4;

public class Publisher { // clasa ce retine informatii despre o editura
	private String name; // numele editurii
	
	public Publisher() { // constructor implicit
		this.name = new String();
	}
	
	public Publisher(String name) { // constructor explicit
		this.name = name;
	}
	
	public String getPublisherName() { // getter pentru numele editurii
		return this.name;
	}
}
