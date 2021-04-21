package lab4;
import java.sql.*;

public class RepoSQL extends Repo{
	 private String db; // variabila ce retine numele bazei de date folosite
	 
	 public RepoSQL() { // constructor implicit
		 super();
	 }
	 
	 public RepoSQL(String db) {
		 super();
		 this.db = db;
		 this.loadFromDB();
	 }
	 
	 private Connection connect() { // metoda ce realizeaza conexiunea la baza de date SQLite
	        // Stringul de conexiune SQLite
	        String url = "jdbc:sqlite:" + this.db;
	        Connection conn = null;
	        try {
	        	// Incercam conexiunea la baza de date
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn; // returnam conexiunea, daca aceasta s-a facut fara erori
	    }
	 
	 public void saveToDB() {
		 String sql = "INSERT INTO BOOKS (ID, TITLE, AUTHOR, RELEASEYEAR, PRICE, PUBLISHER, QUOTA)"
		 		+ "VALUES (?, ?, ?, ?, ?, ?, ?);"; // String pentru prepared statement, folosit
		 // sa adaugam elemente in baza de date
		 try (Connection conn = this.connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
			 // Stergem tabelul curent
			 Statement stmt = null;
			 stmt = conn.createStatement();
			 String dropTable = "DROP TABLE BOOKS;";
			 stmt.executeUpdate(dropTable);
			 String createTable = "CREATE TABLE BOOKS"
					 + "(ID INTEGER NOT NULL,"
					 + "TITLE TEXT,"
					 + "AUTHOR TEXT,"
					 + "RELEASEYEAR INTEGER,"
					 + "PRICE REAL,"
					 + "PUBLISHER TEXT,"
					 + "QUOTA TEXT );"; // String pentru recrearea tabelului
			 stmt.executeUpdate(createTable);
			 stmt.close();
			 
			 //Inseram valorile in baza de date
	           for(Book b : this.values) {
	        	   pstmt.setInt(1, b.getID());
	        	   pstmt.setString(2,  b.getTitle());
	        	   pstmt.setString(3, b.getAuthor());
	        	   pstmt.setInt(4, b.getReleaseYear());
	        	   pstmt.setFloat(5,  b.getPrice());
	        	   pstmt.setString(6, b.getPublisher());
	        	   pstmt.setString(7, b.getQuota());
	        	   pstmt.executeUpdate();
	           }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	 }
	 
	 public void loadFromDB() {
		 String sql = "SELECT * FROM BOOKS"; // obtinem toate valorile din baza de date
		 this.clearRepo(); // curatam lista curenta din memorie
	        
	        try (Connection conn = this.connect();
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){ // obtinem ResultSetul acelui SELECT
	            
	            // facem un loop prin result setul aferent clauzei SELECT
	            while (rs.next()) { // obtinem valoarea inregistrarii curente pentru fiecare coloana
	                int id = rs.getInt("ID");
	                String title = rs.getString("TITLE");
	                String author = rs.getString("AUTHOR");
	                int releaseYear = rs.getInt("RELEASEYEAR");
	                float price = rs.getFloat("PRICE");
	                String publisher = rs.getString("PUBLISHER");
	                String quota = rs.getString("QUOTA");
	                Book b = new Book(id, title, author, releaseYear, price, publisher, quota);
	                // se creeaza cartea cu acele atribute
	                this.addElem(b); // o adaugam in lista
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	 }
	 
	 public void finalize() {
		 this.saveToDB();
		 this.clearRepo();
	 }
}
