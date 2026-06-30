package library.book.management;

public class Book {
	
	private String title;
	private String author;
	private boolean available = true;
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	public void borrowBook() {
		if (available) {
			available = false;
		} else {
			System.out.println("Book is already borrowed.");
		}
	}
	
	public void returnBook() {
		if (!available) {
			available = true;
		}
	}
	
	public void getInfo() {
		System.out.println("Title: " + title);
		System.out.println("Author: " + author);
		System.out.println("Available: " + available + "\n");
	}
	
	public String getTitle() {
		return title;
	}

}
