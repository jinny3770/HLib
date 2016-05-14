package com.swdesign.hlib.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.swdesign.hlib.dao.BorrowInfoDAO;

public class ReturnManager implements IReturn{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		BorrowInfoDAO dao = new BorrowInfoDAO();
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String memberID = request.getParameter("memberID");
		String ISBN = request.getParameter("ISBN");
	}
	
}
