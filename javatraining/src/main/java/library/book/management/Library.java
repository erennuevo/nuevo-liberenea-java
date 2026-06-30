package library.book.management;

import java.util.ArrayList;

public class Library {
	
	ArrayList<Book> books = new ArrayList<>();
	
	public void addBook(Book b) {
		books.add(b);
	}
	
	public void showAllBooks() {
		for (Book b : books) {
			b.getInfo();
		}
	}
	
	public void borrowBook(String title) {
        Book foundBook = books.stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElse(null);
        if (foundBook.equals(null)) {
        	System.out.println("This book was not found.");
        } else {
    		foundBook.borrowBook();
        }
	}
	
	public void returnBook(String title) {
		Book foundBook = books.stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElse(null);
		if (foundBook.equals(null)) {
        	System.out.println("This book was not found.");
        } else {
    		foundBook.returnBook();
        }
	}

}
