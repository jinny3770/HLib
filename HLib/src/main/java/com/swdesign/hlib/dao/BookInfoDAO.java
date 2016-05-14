package com.swdesign.hlib.dao;

import com.swdesign.hlib.domain.BookDomain;

public class BookInfoDAO {

	public BookDomain getBookInfo(String ISBN) {
		BookDomain bookDomain = new BookDomain();
		
		
		// DB에서 정보 꺼내오는 역할.
		
		return bookDomain;
	}
	
	public void saveBookInfo(BookDomain bookDomain) {
		// DB에 book정보 저장
	}
}
