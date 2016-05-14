package com.swdesign.hlib.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.swdesign.hlib.dao.BookInfoDAO;
import com.swdesign.hlib.dao.BorrowInfoDAO;
import com.swdesign.hlib.dao.MemberInfoDAO;
import com.swdesign.hlib.domain.BookDomain;
import com.swdesign.hlib.domain.BorrowInfoDomain;
import com.swdesign.hlib.domain.MemberDomain;

public class ReturnManager implements IReturn{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		BorrowInfoDAO borrowDao = new BorrowInfoDAO();
		BookInfoDAO bookDao = new BookInfoDAO();
		MemberInfoDAO memberDao = new MemberInfoDAO();
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		// 기본정보 load
		String memberID = request.getParameter("memberID");
		String ISBN = request.getParameter("ISBN");
		
		
		// 책 반납 처리 후 저장
		BookDomain bookDomain = bookDao.getBookInfo(ISBN);
		bookDomain.changeBookState();
		bookDao.saveBookInfo(bookDomain);
		
		
		// 대여 이력 저장
		BorrowInfoDomain borrowDomain = borrowDao.getBorrowInfo(memberID, ISBN);
		
		Date today = new Date();
		borrowDomain.setReturnDate(today);
		borrowDao.SaveBorrowInfo(borrowDomain);
		
		
		// 회원 정보 load, 대여 가능 권수 수정
		MemberDomain memberDomain = memberDao.getMember(memberID);
		memberDomain.returnHandler();
		
		
		//point 처리에 필요한 정보 load
		Date borrowedDate = borrowDomain.getBorrowedDate();
		Date returnDate = borrowDomain.getReturnDate();
		int borrowableTerm = memberDomain.getBorrowableTerm();
		
	
		// 포인트 계산
		int point = calcPoint(borrowedDate, returnDate, borrowableTerm);
		memberDomain.setChangedPoint(point);
		
		// member 저장
		memberDao.saveMember(memberDomain);
	}
	
	private int calcPoint(Date borrowedDate, Date returnDate, int borrowableTerm) {
		
		Calendar borrowCal = Calendar.getInstance();
		Calendar returnCal = Calendar.getInstance();
		
		borrowCal.setTime(borrowedDate);
		returnCal.setTime(returnDate);
		
		long diffLong = returnCal.getTimeInMillis() - borrowCal.getTimeInMillis();
		int diff = (int) diffLong/(24 * 60 * 60 * 1000);
		
		if(diff <= borrowableTerm ) {
			return 10;
		}else {
			return -5 * diff;
		}
	}
	
}
