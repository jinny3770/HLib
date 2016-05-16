package com.swdesign.hlib.domain;

public class BookDomain {

	String ISBN;
	String bookName;
	boolean bookState;
	
	public BookDomain() { }
	
	public BookDomain(String ISBN, String bookName, boolean bookState) {
		this.ISBN = ISBN;
		this.bookName = bookName;
		this.bookState = bookState;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public boolean getBookState() {
		return bookState;
	}

	public void setBookState(boolean bookState) {
		this.bookState = bookState;
	}

	public void changeBookState() {
		bookState = (!bookState);
	}
	
}
