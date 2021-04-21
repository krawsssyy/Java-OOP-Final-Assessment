package lab4;

public class Pair<T1, T2> { // clasa cu template ce implemnteaza std::pair din c++
	//(doar functionalitatile nevoite in program)
	T1 first; // primul element al perechii
	T2 second; // al doilea element al perechii
	
	public Pair(T1 first, T2 second) { // constructor explicit
		this.first = first;
		this.second = second;
	}
	public T1 first() { // metoda ce returneaza primul element din pereche
		return this.first;
	}
	
	public T2 second() { // metoda ce returneaza al doilea element din pereche
		return this.second;
	}
}
