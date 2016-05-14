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
		
		// �⺻���� load
		String memberID = request.getParameter("memberID");
		String ISBN = request.getParameter("ISBN");
		
		
		// å �ݳ� ó�� �� ����
		BookDomain bookDomain = bookDao.getBookInfo(ISBN);
		bookDomain.changeBookState();
		bookDao.saveBookInfo(bookDomain);
		
		
		// �뿩 �̷� ����
		BorrowInfoDomain borrowDomain = borrowDao.getBorrowInfo(memberID, ISBN);
		
		Date today = new Date();
		borrowDomain.setReturnDate(today);
		borrowDao.SaveBorrowInfo(borrowDomain);
		
		
		// ȸ�� ���� load, �뿩 ���� �Ǽ� ����
		MemberDomain memberDomain = memberDao.getMember(memberID);
		memberDomain.returnHandler();
		
		
		//point ó���� �ʿ��� ���� load
		Date borrowedDate = borrowDomain.getBorrowedDate();
		Date returnDate = borrowDomain.getReturnDate();
		int borrowableTerm = memberDomain.getBorrowableTerm();
		
	
		// ����Ʈ ���
		int point = calcPoint(borrowedDate, returnDate, borrowableTerm);
		memberDomain.setChangedPoint(point);
		
		// member ����
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
