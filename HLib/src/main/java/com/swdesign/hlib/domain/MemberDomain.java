package com.swdesign.hlib.domain;

public class MemberDomain {

	String memberID;
	int borrowableTerm;
	int monthPoint;
	int allPoint;
	int borrowedBookCount;
	int borrowableBookCount;

	
	public MemberDomain() { }
	
	public MemberDomain(String memberID, int borrowableTerm, int monthPoint,
			int allPoint, int borrowedBookCount, int borrowableBookCount) {
		
		this.memberID = memberID;
		this.borrowableTerm = borrowableTerm;
		this.monthPoint = monthPoint;
		this.allPoint = allPoint;
		this.borrowedBookCount = borrowedBookCount;
		this.borrowableBookCount = borrowableBookCount;
	}

	public void returnHandler() {
		borrowedBookCount++;
	}
	
	public void setChangedPoint(int point) {
		allPoint += point;
		monthPoint += point;
	}
	
	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public int getBorrowableTerm() {
		return borrowableTerm;
	}

	public void setBorrowableTerm(int borrowableTerm) {
		this.borrowableTerm = borrowableTerm;
	}

	public int getMonthPoint() {
		return monthPoint;
	}

	public void setMonthPoint(int monthPoint) {
		this.monthPoint = monthPoint;
	}

	public int getAllPoint() {
		return allPoint;
	}

	public void setAllPoint(int allPoint) {
		this.allPoint = allPoint;
	}

	public int getBorrowedBookCount() {
		return borrowedBookCount;
	}

	public void setBorrowedBookCount(int borrowedBookCount) {
		this.borrowedBookCount = borrowedBookCount;
	}

	public int getBorrowableBookCount() {
		return borrowableBookCount;
	}

	public void setBorrowableBookCount(int borrowableBookCount) {
		this.borrowableBookCount = borrowableBookCount;
	}
	
	
}
