package lab4;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;

public class Console {
	private Service s; // variabila ce retine serviceul curent
	private Scanner scn;
	
	public Console() { // constructor implicit
		this.s = new Service();
		this.scn = new Scanner(System.in);
	}
	
	public Console(Service s) { // constructor explicit
		this.s = s;
		this.scn = new Scanner(System.in);
	}
	
	private static void showMenu() {
		System.out.println("1. Adaugati carte");
		System.out.println("2. Cautati carti dupa cota/id");
		System.out.println("3. Afisati toate cartile");
		System.out.println("4. Modificati o carte");
		System.out.println("5. Stergeti o carte");
		System.out.println("6. Afisati toate cartile si valoarea lor totala");
		System.out.println("7. Afisati toate cartile scrise de un autor dat");
		System.out.println("8. Afisati toate cartile aparute intre doi ani dati");
		System.out.println("9. Afisati toate cartile aparute la o anumita editura");
		System.out.println("10. Afisati toate cartile care au pretul mai mic decat o valoare data");
		System.out.println("11. Iesire");
		System.out.print("Optiune : ");
	}
	
	private void printBooks(Vector<Book> values) {
		System.out.println("+-------+-----------------+---------------+-------+--------+---------------+--------+");
		System.out.println("| Id    |     Titlu       |     Autor     |An.publ|  Pret  |      Editura  |  Cota  |");
		System.out.println("+-------+-----------------+---------------+-------+--------+---------------+--------+");
		String alignment = "| %-5d | %-15s | %-13s | %-5d | %-6s | %-13s | %-6s | \n";
		for(Book b : values) {
			DecimalFormat twoDForm = new DecimalFormat("#.##");
			String price = twoDForm.format(b.getPrice());
			System.out.format(alignment, b.getID(), b.getTitle(), b.getAuthor(), b.getReleaseYear(), price, b.getPublisher(), b.getQuota());
		}
		System.out.println("+-------+-----------------+---------------+-------+--------+---------------+--------+");
		System.out.println("");
	}
	private void handleAddBook() {
		try {
			System.out.print("Doriti sa introduceti o carte deja existenta? y/n : ");
			String opt = this.scn.next();
			if(opt.equals("y")) {
				System.out.print("Introduceti id-ul acelei carti : ");
				int id = this.scn.nextInt();
				Book b = this.s.getBookByID(id);
				if(b == null) 
					System.out.println("Cartea cu acest id nu a fost gasita! \n");
				else {
					this.s.addBook(b.getID(), b.getTitle(), b.getAuthor(), b.getReleaseYear(), b.getPrice(), b.getPublisher());
					System.out.println("Carte introdusa cu succes! \n");
					}
			}
			else if(opt.equals("n")) {
				System.out.print("Introduceti id-ul cartii : ");
				int id = this.scn.nextInt();
				if(this.s.getBookByID(id) != null)
					throw new Exception("Cartea cu acel id exista deja!");
				System.out.print("Introduceti titlul cartii : ");
				String title = this.scn.next();
				System.out.print("Introduceti numele autorului: ");
				String auth = this.scn.next();
				System.out.print("Introduceti anul publicarii : ");
				int releaseYear = this.scn.nextInt();
				System.out.print("Introduceti pretul cartii : ");
				float price = this.scn.nextFloat();
				System.out.print("Introduceti editura : ");
				String publ = this.scn.next();
				this.s.addBook(id,  title,  auth,  releaseYear,  price,  publ);
				System.out.println("Carte introdusa cu succes! \n");
			}
			else
				System.out.println("Optiune invalida! \n");
		}
		catch(Exception e) {
			System.out.println(e.getMessage() + '\n');
		}
	}
	
	private void handleBookSearch() {
		System.out.print("Introduceti cota/id : ");
		String res = this.scn.next();
		String[] rez = res.split("\\.");
		if(rez.length == 1) {
			int id = Integer.parseInt(rez[0]);
			Book b = this.s.getBookByID(id);
			if(b == null)
				System.out.println("Cartea cu id-ul specificat nu a fost gasita! \n");
			else {
				Vector<Book> bb = new Vector<Book>();
				bb.add(b);
				this.printBooks(bb);
				}
		}
		else if(rez.length == 2) {
			Book b = this.s.getBookByQuota(res);
			if(b == null)
				System.out.println("Cartea cu cota specificata nu a fost gasita! \n");
			else {
				Vector<Book> bb = new Vector<Book>();
				bb.add(b);
				this.printBooks(bb);
				}
		}
		else
			System.out.println("Format incorect pentru cota/id! \n ");
	}
	
	private void handleShowBooks() {
		Vector<Book> values= this.s.getElems();
		this.printBooks(values);
	}
	
	private void handleModifyBook() {
		try {
			System.out.print("Introduceti vechea cota a cartii : ");
			String oldQuota = this.scn.next();
			if(this.s.getBookByQuota(oldQuota) == null)
				throw new Exception("Cartea cu acea cota nu a fost gasita!");
			System.out.print("Introduceti noul id al cartii : ");
			int id = this.scn.nextInt();
			Book b = this.s.getBookByID(id);
			System.out.print("Introduceti noul titlu al cartii : ");
			String title = this.scn.next();
			if(b != null && !title.equals(b.getTitle()))
				throw new Exception("Ati introdus date gresite pentru cartea cu id-ul deja existent!");
			System.out.print("Introduceti noul nume de autor : ");
			String auth = this.scn.next();
			System.out.print("Introduceti noul an al  publicatiei : ");
			int releaseYear = this.scn.nextInt();
			System.out.print("Introduceti noul pret al  cartii : ");
			float price = this.scn.nextFloat();
			System.out.print("Introduceti noua editura : ");
			String publ = this.scn.next();
			this.s.updateBook(oldQuota,  id,  title,  auth,  releaseYear,  price,  publ);
			System.out.println("Carte modificata cu succes! \n");
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage() + '\n');
		}
	}
	
	private void handleDeleteBook() {
		try {
			System.out.print("Introduceti cota cartii de sters : ");
			String quota = this.scn.next();
			if(this.s.getBookByQuota(quota) == null)
				throw new Exception("Cartea cu acea cota nu a fost gasita!");
			this.s.deleteBook(quota);
			System.out.println("Carte stearsa cu succes! \n");
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage() + '\n');
		}
	}
	
	private void handleBooksAndTotals() {
		Vector<Pair<Book, Float>> values = this.s.getBooksAndTotalPrice();
		System.out.println("+-------+-----------------+---------------+-------+--------+---------------+");
		System.out.println("| Id    |     Titlu       |     Autor     |An.publ|  Pret  |      Editura  |");
		System.out.println("+-------+-----------------+---------------+-------+--------+---------------+");
		String alignment = "| %-5d | %-15s | %-13s | %-5d | %6.2f | %-13s | \n";
		for(Pair<Book, Float> p : values) {
			Book b = p.first();
			float f = p.second();
			//DecimalFormat twoDForm = new DecimalFormat("#.00");
			//String totalPrice = twoDForm.format(f);
			System.out.format(alignment,  b.getID(), b.getTitle(), b.getAuthor(), b.getReleaseYear(), f, b.getPublisher());
		}
		System.out.println("+-------+-----------------+---------------+-------+--------+---------------+");
		System.out.println("");
	}
	
	private void handleBooksByAuthor() {
		System.out.print("Introduceti numele autorului : ");
		String auth = this.scn.next();
		Vector<Book> res = this.s.getBooksByAuthor(auth);
		if(res == null)
			System.out.println("Nu a fost gasita nicio carte scrisa de acest autor! \n");
		else {
			System.out.println("Cartile scrise de " + auth + " sunt :");
			this.printBooks(res);
		}
	}
	
	private void handleBooksBetweenYears() {
		try {
			System.out.print("Introduceti primul an : ");
			int firstYear = this.scn.nextInt();
			System.out.print("Introduceti al doilea an : ");
			int secondYear = this.scn.nextInt();
			if(firstYear > secondYear) {
				int aux = firstYear;
				firstYear = secondYear;
				secondYear = aux;
			}
			Vector<Book> res = this.s.getBooksBetweenTwoYears(firstYear,  secondYear);
			if(res == null)
				System.out.println("Nu a fost gasita nicio intre acesti 2 ani! \n");
			else {
				System.out.println("Cartile publicate intre " + String.valueOf(firstYear) + " si " + String.valueOf(secondYear) + " sunt : ");
				this.printBooks(res);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage() + '\n');
		}
	}
	
	private void handleBooksByPublisher() {
		try {
			System.out.print("Introduceti numele editurii : ");
			String publ = this.scn.next();
			Vector<Book> res = this.s.getBooksByPublisher(publ);
			if(res == null)
				System.out.println("Nu a fost gasita nicio carte publicata de acea editura! \n");
			else {
				System.out.println("Cartile publicate de " + publ + " sunt : ");
				this.printBooks(res);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage() + '\n');
		}
	}
	
	private void handleBooksLowerPrice() {
		try {
			System.out.print("Introduceti pretul maxim : ");
			float price = this.scn.nextFloat();
			Vector<Book> res = this.s.getBooksLowerThanAPrice(price);
			if(res == null)
				System.out.println("Nu exista carti mai ieftine decat pretul dat! \n");
			else {
				System.out.println("Cartile cu pretul mai mic decat " + String.valueOf(price) + " sunt : ");
				this.printBooks(res);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage() + '\n');
		}
	}
	
	public void run() {
		String opt = "";
		showMenu();
		opt = this.scn.next();
		while(!opt.equals("11")) {
			if(opt.equals("1"))
				this.handleAddBook();
			else if(opt.equals("2"))
				this.handleBookSearch();
			else if(opt.equals("3"))
				this.handleShowBooks();
			else if(opt.equals("4"))
				this.handleModifyBook();
			else if(opt.equals("5"))
				this.handleDeleteBook();
			else if(opt.equals("6"))
				this.handleBooksAndTotals();
			else if(opt.equals("7"))
				this.handleBooksByAuthor();
			else if(opt.equals("8"))
				this.handleBooksBetweenYears();
			else if(opt.equals("9"))
				this.handleBooksByPublisher();
			else if(opt.equals("10"))
				this.handleBooksLowerPrice();
			else 
				System.out.println("Optiune invalida!\n");
			showMenu();
			opt = this.scn.next();
		}
		this.finalize();
	}
	
	protected void finalize() {
		this.scn.close();
		this.s.finalize();
	}
}
