package com.swdesign.hlib.domain;

import java.util.Date;

public class BorrowInfoDomain {

	Date borrowedDate;
	Date returnDate;
	String memberID;
	String ISBN;
	
	public BorrowInfoDomain() {
		
	}

	public BorrowInfoDomain(Date borrowedDate, Date returnDate, String memberID, String ISBN) {
		this.borrowedDate = borrowedDate;
		this.returnDate = returnDate;
		this.memberID = memberID;
		this.ISBN = ISBN;
	}

	
	
	public Date getBorrowedDate() {
		return borrowedDate;
	}

	public void setBorrowedDate(Date borrowedDate) {
		this.borrowedDate = borrowedDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	
	
	
}
