

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Loan {
	private User user;
	private Book book;
	private LocalDateTime loanDate; //Dia do empréstimo
	private LocalDateTime returnDate; //Data para devolução
	private LocalDateTime actualReturnDate; // Data real de devolução
	private double fine= 5.0; //Multa de 5 reais por dia de atraso 
	
	public Loan(User user, Book book) {
		if (book.Available()) {
			Scanner sc = new Scanner(System.in);
			//recebendo o prazo de devolução
			System.out.println("Insira o prazo(em dias) para a devolução do livro: ");
			int days = sc.nextInt();
			
			this.user = user;
			this.book = book;
			this.loanDate = LocalDateTime.now();
			this.returnDate = loanDate.plusDays(days);
			this.actualReturnDate = null; //starta como nulo
			book.changeAvailability();
			user.addBorrowedBook(book);
		}else {
			System.out.println("O livro "+book.getName()+" não está disponível para empréstimo");
		}
	}
	
	public User getUser() {
		return user;
	}
	
	public Book getBook() {
		return book;
	}
	
	// Método para registrar a data de devolução
    public void returnBook() {
        this.actualReturnDate = LocalDateTime.now();  // Data real de devolução
        book.changeAvailability();  // Marca o livro como disponível novamente
    }
	
    //Métoo para cálculo de multa
    public double calculateFine() {
        if (actualReturnDate != null && actualReturnDate.isAfter(returnDate)) { // se ainda não devolveu ou devolveu com atraso
            long delayDays = ChronoUnit.DAYS.between(returnDate, actualReturnDate);  // Calcula os dias de atraso
            return delayDays * fine;  // Retorna a multa com base nos dias de atraso
        } else {
            return 0;  // Sem atraso ou ainda não devolvido
        }
    }

	
	public String LoanInfo() {
		String info =  "Livro: "+book.getName()+ ", Cliente: "+user.getName()+", Data de empréstimo: "+loanDate+", Data para Devolução: "+returnDate;
		// Verifica se o livro foi devolvido
		double fine = calculateFine();
	    if (actualReturnDate != null) { //já teve retorno
	        info += ", Data de Devolução Real: " + actualReturnDate;
	        if(fine > 0) {
		    	long delayDays = ChronoUnit.DAYS.between(returnDate, actualReturnDate);
		    	info += ", Dias de Atraso: "+delayDays+", Multa: "+fine;
	        }else{
	        	info += ", Devolvido dentro do prazo";
	        }
	    }else {
	    	info+= ", O livro ainda não foi devolvido";
	    }
	    return info;
		
	}

	
}

