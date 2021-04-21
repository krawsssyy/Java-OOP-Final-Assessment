package lab4;

public class LAB4 {

	public static void main(String[] args) {
		RepoSQL repo = new RepoSQL("test.db");
		Service serv = new Service(repo);
		try {
			gui window = new gui(serv);
		} catch (Exception e) {
			System.out.println("A aparut o eroare la initializarea interfetei grafice.\nVa rugam reincercati!");
		}

	}
	

}
