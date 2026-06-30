package library.book.management;

public class Main {

	public static void main(String args[]) {
		Library library = new Library();
		
		Book book1 = new Book("Harry Potter", "JK Rowling", true);
		Book book2 = new Book("Percy Jackson", "Rick Riordan", true);
		Book book3 = new Book("Alice in Wonderland", "Lewis Carroll", true);
		
		System.out.println("-- ADDING BOOKS... --");
		library.addBook(book1);
		library.addBook(book2);
		library.addBook(book3);	
		library.showAllBooks();
		
		System.out.println("-- BORROWING A BOOK... --");
		library.borrowBook("Harry Potter");
		library.showAllBooks();
		
		System.out.println("-- RETURNING A BOOK... --");
		library.returnBook("Harry Potter");
		library.showAllBooks();
	}
}
