package ru.tpu.javaEElabs.lab1;

public class Product {
	
private int id;
private String genre;
private String author;
private String title;

public Product(int id, String description, String rate, String quantity) {
this.id = id;
this.genre = genre;
this.author = author;
this.title = title;
}

public int getId() {
return id;
}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
