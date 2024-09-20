
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String name;
    private String CPF;
    private String type; // estudante ou professor
    private List<Book> borrowedBooks;

    @Override
	public String toString() {
		return "Usuário [nome=" + name + ", CPF=" + CPF + ", tipo=" + type + ", livros=" + borrowedBooks + "]";
	}

	public User(String name, String CPF, String type) {
        this.name = name;
        this.CPF = CPF;
        setType(type); // Validar type no construtor
        this.borrowedBooks = new ArrayList<>(); // 
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
		return borrowedBooks;
	}

	public void addBorrowedBook(Book book) {
		this.borrowedBooks.add(book);
	}

	public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getType() {
        return type;
    }

    Scanner sc = new Scanner(System.in);

    public void setType(String type) {
        // IgnoreCase é para ignorar se for maiúscula ou minúscula
        if (type.equalsIgnoreCase("estudante") || type.equalsIgnoreCase("professor")) {
            this.type = type;
        } else {
            System.out.println("Tipo inválido! Deve ser 'estudante' ou 'professor'.");
            System.out.println("Informe novamente o tipo: ");
            String newType = sc.next();
            setType(newType);
        }
    }
}
