package lab4;
import java.util.HashMap;
import java.util.Vector;

public class Service {
	private RepoSQL repo; // variabila ce retine repo-ul curent utilizat
	
	public Service() { // constructor implicit
		this.repo = new RepoSQL();
	}
	
	public Service(RepoSQL repo) { // constructor cu parametrii
		this.repo = repo;
	}
	
	private int getSecondQuota(int id) { // metoda ce returneaza cota particulara a unei carti
		// aceasta metoda obtine maximul dintre cotele particulare pentru acea carte
		// dupa care returneaza acel numar + 1 
		int appearances = 0;
		Vector<Book> values = this.repo.getElems();
		for(Book b : values) {
			if(b.getID() == id)
				{
				String quota = b.getQuota();
				String[] res = quota.split("\\.");
				quota = res[1];
				int val = Integer.parseInt(quota);
				if(val > appearances)
					appearances = val;
				}
		}
		return appearances + 1;
	}
	
	public void addBook(int id, String title, String authName, int releaseYear, float price, String publName) {
		// metoda ce adauga o carte in repo
		int secondQuota = this.getSecondQuota(id);
		String quota = String.valueOf(id) + "." + String.valueOf(secondQuota);
		Book b = new Book(id, title, authName, releaseYear, price, publName, quota);
		this.repo.addElem(b);
	}
	
	public void updateBook(String quota, int newID, String newTitle, String newAuthName,
			int newReleaseYear, float newPrice, String newPublName) {
		// metoda ce face update unei carti din repo
		int secondQuota = this.getSecondQuota(newID);
		String newQuota = String.valueOf(newID) + "." + String.valueOf(secondQuota);
		Book newB = new Book(newID,newTitle, newAuthName, newReleaseYear, newPrice, newPublName, newQuota);
		this.repo.update(quota, newB);
	}
	
	public void deleteBook(String quota) {
		// metoda ce sterge o carte din repo
		this.repo.delete(quota);
	}
	
	public Book getBookByQuota(String quota) { // metoda ce returneaza o carte dupa cota
		Vector<Book> values = this.repo.getElems();
		for(Book b : values)
			if(b.getQuota().equals(quota))
				return b;
		return null;
	}
	
	public Book getBookByID(int id) { // metoda ce imi returneaza o carte dupa id
		Vector<Book> values = this.repo.getElems();
		for(Book b : values)
			if(b.getID() == id)
				return b;
		return null;
	}
	
	@SuppressWarnings("null")
	public Vector<Pair<Book, Float>> getBooksAndTotalPrice() { 
		// metoda ce creaza un vector de perechi
		// cu cartile curente si valoarea lor totala
		HashMap<Integer, Float> values = new HashMap<Integer, Float>();
		Vector<Book> books = this.repo.getElems();
		for(Book b : books) {
			if(values.get(b.getID()) == null)
				values.put(b.getID(), b.getPrice());
			else
				values.replace(b.getID(), (float) (values.get(b.getID()) + b.getPrice()));
		}
		Vector<Pair<Book, Float>> retVect = new Vector<Pair<Book, Float>>();
		for(int k : values.keySet()) {
			Book b = this.getBookByID(k);
			Pair<Book, Float> p = new Pair<Book, Float>(b, values.get(k));
			retVect.add(p);
		}
		return retVect;
	}
	
	public Vector<Book> getBooksByAuthor(String authName) {
		// metoda ce returneaza toate cartile scrise de un anume autor
		Vector<Book> result = new Vector<Book>();
		Vector<Book> values = this.repo.getElems();
		for(Book b : values)
			if(b.getAuthor().equals(authName))
				result.add(b);
		return result.isEmpty() ? null : result;
	}
	
	public Vector<Book> getBooksBetweenTwoYears(int firstYear, int secondYear) {
		// metoda ce returneaza toate cartile scrise intre doi ani dati
		Vector<Book> result = new Vector<Book>();
		Vector<Book> values = this.repo.getElems();
		for(Book b : values)
			if(firstYear <= b.getReleaseYear() && b.getReleaseYear() <= secondYear)
				result.add(b);
		return result.isEmpty() ? null : result;
	}
	
	public Vector<Book> getBooksByPublisher(String publName){
		// metoda ce returneaza toate cartile de la o anumita editura
		Vector<Book> result = new Vector<Book>();
		Vector<Book> values = this.repo.getElems();
		for(Book b : values)
			if(b.getPublisher().equals(publName))
					result.add(b);
		return result.isEmpty() ? null : result;
	}
	
	public Vector<Book> getBooksLowerThanAPrice(Float maxPrice) {
		// metoda ce returneaza toate cartile care au un pret mai mic decat un 
		// anume pret dat
		Vector<Book> result = new Vector<Book>();
		Vector<Book> values = this.repo.getElems();
		for(Book b : values)
			if((maxPrice - b.getPrice()) > 0.000000001f)
					result.add(b);
		return result.isEmpty() ? null : result;
	}
	
	public Vector<Book> getElems() {
		return this.repo.getElems();
	}
	
	public void finalize() {
		this.repo.finalize();
	}
	
}
