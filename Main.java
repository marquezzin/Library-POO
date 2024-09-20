
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        List<User> users = new ArrayList<>(); // Lista para armazenar usuários
        List<Book> catalog = new ArrayList<>(); // Catálogo de livros
        List<Loan> loans = new ArrayList<>(); // Lista de empréstimos

        int option = 0;

        while (option != -1) {
            System.out.println("----- MENU -----");
            System.out.println("0 - Exibir usuários do sistema");
            System.out.println("1 - Adicionar Usuário");
            System.out.println("2 - Exibir Informações do Usuário");
            System.out.println("3 - Adicionar Livro ao Catálogo");
            System.out.println("4 - Exibir Catálogo de Livros");
            System.out.println("5 - Realizar Empréstimo");
            System.out.println("6 - Exibir Detalhes do Empréstimo");
            System.out.println("7 - Realizar Devolução de Livro");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opção: ");
            option = sc.nextInt();
            sc.nextLine(); // Limpar o buffer após nextInt() para evitar bug

            switch (option) {
                case 0 -> {
                	if (users.isEmpty()) {
						System.out.println("Não há usuários cadastrados no sistema");
					}else {
						System.out.println("Usuários cadastrados: ");
						for(User user:users) {
							System.out.println(user);
						}
					}
                }
            
                case 1 -> {
                    // Adicionar usuário
                    System.out.print("Nome do Usuário: ");
                    String name = sc.nextLine();
                    System.out.print("CPF do Usuário: ");
                    String cpf = sc.nextLine();
                    System.out.print("Tipo do Usuário (estudante/professor): ");
                    String type = sc.nextLine();
                    User user = new User(name, cpf, type);
                    users.add(user);
                    System.out.println("Usuário adicionado com sucesso!");
                }
                case 2 -> {
                    // Exibir informações do usuário
                    if (users.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        System.out.print("Informe o nome do usuário para exibir informações: ");
                        String name = sc.nextLine();
                        for (User user : users) {
                            if (user.getName().equalsIgnoreCase(name)) {
                                System.out.println(user); //método toString
                            }
                        }
                    }
                }
                case 3 -> {
                    // Adicionar livro ao catálogo
                    System.out.print("Nome do Livro: ");
                    String bookName = sc.nextLine();
                    System.out.print("Categoria do Livro: ");
                    String category = sc.nextLine();
                    System.out.print("Tipo do Livro (impresso/digital): ");
                    String type = sc.nextLine();
                    System.out.print("Autor do Livro: ");
                    String author = sc.nextLine();
                    Book book = new Book(bookName, category, type, author);
                    catalog.add(book);
                    System.out.println("Livro adicionado ao catálogo.");
                }
                case 4 -> {
                    // Exibir catálogo de livros
                    if (catalog.isEmpty()) {
                        System.out.println("O catálogo está vazio.");
                    } else {
                        System.out.println("Deseja filtrar sua consulta? (S/N)");
                        String choice = sc.nextLine();
                        
                        if (choice.equalsIgnoreCase("S")) {
                            System.out.println("Escolha um dos filtros: (nome/autor/categoria/tipo/disponiveis)");
                            String filterType = sc.nextLine();
                            List<Book> filteredBooks = new ArrayList<>();
                            
                            for (Book book : catalog) {
                                switch (filterType.toLowerCase()) {
                                    case "nome":
                                        System.out.print("Digite o nome do livro: ");
                                        String nameValue = sc.nextLine();
                                        if (book.getName().equalsIgnoreCase(nameValue)) {
                                            filteredBooks.add(book);
                                        }
                                        break;
                                    case "autor":
                                        System.out.print("Digite o autor do livro: ");
                                        String authorValue = sc.nextLine();
                                        if (book.getAuthor().equalsIgnoreCase(authorValue)) {
                                            filteredBooks.add(book);
                                        }
                                        break;
                                    case "categoria":
                                        System.out.print("Digite a categoria do livro: ");
                                        String categoryValue = sc.nextLine();
                                        if (book.getCategory().equalsIgnoreCase(categoryValue)) {
                                            filteredBooks.add(book);
                                        }
                                        break;
                                    case "tipo":
                                        System.out.print("Digite o tipo do livro (impresso/digital): ");
                                        String typeValue = sc.nextLine();
                                        if (book.getType().equalsIgnoreCase(typeValue)) {
                                            filteredBooks.add(book);
                                        }
                                        break;
                                    case "disponiveis":
                                        if (book.Available()) {
                                            filteredBooks.add(book);
                                        }
                                        break;
                                    default:
                                        System.out.println("Filtro inválido.");
                                        break;
                                }
                            }
                            
                            if (filteredBooks.isEmpty()) {
                                System.out.println("Nenhum livro encontrado com o filtro fornecido.");
                            } else {
                                System.out.println("Livros filtrados:");
                                for (Book book : filteredBooks) {
                                    System.out.println(book);
                                }
                            }
                        } else if (choice.equalsIgnoreCase("N")) {
                            System.out.println("Catálogo de Livros:");
                            for (Book book : catalog) {
                                System.out.println(book);
                            }
                        } else {
                            System.out.println("Opção Inválida");
                        }
                    }
                }
                case 5 -> {
                    // Realizar empréstimo
                    if (users.isEmpty() || catalog.isEmpty()) {
                        System.out.println("Necessário ter pelo menos um usuário e um livro no catálogo.");
                    } else {
                        System.out.print("Nome do Usuário: ");
                        String userName = sc.nextLine();
                        User borrower = null;
                        for (User user : users) {
                            if (user.getName().equalsIgnoreCase(userName)) {
                                borrower = user;
                                break;
                            }
                        }
                        if (borrower != null) {
                            System.out.print("Nome do Livro: ");
                            String bookNameForLoan = sc.nextLine();
                            Book bookToLoan = null;
                            for (Book book : catalog) {
                                if (book.getName().equalsIgnoreCase(bookNameForLoan) && book.Available()) {
                                    bookToLoan = book;
                                    break;
                                }
                            }
                            if (bookToLoan != null) {
                                Loan loan = new Loan(borrower, bookToLoan);
                                loans.add(loan);
                                System.out.println("Empréstimo realizado com sucesso!");
                            } else {
                                System.out.println("Livro indisponível para empréstimo.");
                            }
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                    }
                }
                case 6 -> {
                    // Exibir detalhes do empréstimo
                    if (loans.isEmpty()) {
                        System.out.println("Nenhum empréstimo registrado.");
                    } else {
                        System.out.print("Nome do Usuário para verificar empréstimo: ");
                        String userName = sc.nextLine();
                        for (Loan loan : loans) {
                            if (loan.getUser().getName().equalsIgnoreCase(userName)) {
                                System.out.println(loan.LoanInfo());
                            }
                        }
                    }
                }
                case 7 -> {
                    // Realizar devolução de livro
                    if (loans.isEmpty()) {
                        System.out.println("Nenhum empréstimo encontrado.");
                    } else {
                        System.out.print("Nome do Usuário que está devolvendo o livro: ");
                        String userName = sc.nextLine();
                        Loan loanToReturn = null;
                        for (Loan loan : loans) {
                            if (loan.getUser().getName().equalsIgnoreCase(userName)) {
                                loanToReturn = loan;
                                break;
                            }
                        }
                        if (loanToReturn != null) {
                            loanToReturn.returnBook();
                            System.out.println("Livro devolvido com sucesso.");
                            double fine = loanToReturn.calculateFine();
                            if (fine > 0) {
                                System.out.println("Multa por atraso: R$" + fine);
                            }
                        } else {
                            System.out.println("Empréstimo não encontrado.");
                        }
                    }
                }
                case 8 -> {
                    // Sair
                    System.out.println("Saindo...");
                    option = -1;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        
        sc.close();
    }
}
