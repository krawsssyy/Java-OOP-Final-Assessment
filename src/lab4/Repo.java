package lab4;
import java.util.Vector;


public class Repo {
	protected Vector<Book> values = null; // vectorul ce va retine cartile
	
	public Repo() { // constructor implicit
		this.values = new Vector<Book>();
	}
	
	public void addElem(Book b) { // metoda ce adauga o carte in lista
		this.values.add(b);
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Book> getElems() { // metoda ce returneaza lista de carti
		return (Vector<Book>)this.values.clone();
	}
	
	public int getSize() { // metoda ce returneaza dimensiunea listei de carti
		return this.values.size();
	}
	
	protected void clearRepo() { // metoda ce curata toata lista
		this.values.clear();
	}
	
	public void update(String quota, Book newB) { // metoda ce face update unei carti
		// pe baza cotei
		for(int i = 0; i < this.values.size(); i++)
			if(this.values.get(i).getQuota().equals(quota))
				this.values.set(i, newB);
				
	}
	
	public void delete(String quota) { // metoda ce sterge o carte din lista
		// pe baza cotei
		for(Book b : this.values)
			if(b.getQuota().equals(quota))
				{this.values.remove(b);break;}
	}
	
	public void loadFromDB() { // metoda lasat goala pentru suprascriere in mostenire
	}
	
	public void saveToDB() { // metoda lasata goala pentru suprascriere in mostenire
	}
	
}
	
	

