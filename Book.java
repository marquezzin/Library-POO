

import java.util.Scanner;

public class Book {
	private String name;
	private String category;
	private String type; //digital ou impresso
	private String author;
	private boolean disponibility;
		
	@Override
	public String toString() {
		return "Livro [nome=" + name + ", categoria=" + category + ", tipo=" + type + ", autor=" + author
				+ ", disponibilidade=" + disponibility + "]";
	}
	public Book(String name, String category, String type, String author) {
		this.name = name;
		this.category = category;
		setType(type);
		this.author = author;
		this.disponibility = true;
	}
	public boolean Available() {
		return disponibility;
	}
	
	public void changeAvailability() {
		disponibility = !disponibility;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	Scanner sc = new Scanner(System.in);
	public void setType(String type) {
        // IgnoreCase é para ignorar se for maiúscula ou minúscula
        if (type.equalsIgnoreCase("impresso") || type.equalsIgnoreCase("digital")) {
            this.type = type;
        } else {
            System.out.println("Tipo inválido! Deve ser 'impresso' ou 'digital' .");
            System.out.println("Informe novamente o tipo: ");
            String newType = sc.next();
            setType(newType);
        }
    }

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
