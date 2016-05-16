package com.swdesign.hlib.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.swdesign.hlib.domain.BookDomain;
import com.swdesign.hlib.domain.BorrowInfoDomain;

public class BorrowInfoDAO {

	DataSource dataSource;
	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public BorrowInfoDAO() {
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BorrowInfoDomain getBorrowInfo(String memberID, String iSBN) {
		
		BorrowInfoDomain borrowDomain = new BorrowInfoDomain();
		
		try {
			conn = dataSource.getConnection();
			String qeury = "select BorrowedDate from BorrowInfo where MemberID=\"" + memberID + "\", ISBN=\"" + iSBN + "\"";
			preparedStatement = conn.prepareStatement("");
			resultSet = preparedStatement.executeQuery();
			
			
			Date borrowedDate = resultSet.getDate("BorrowedDate");
			
			borrowDomain.setBorrowedDate(borrowedDate);
			borrowDomain.setISBN(iSBN);
			borrowDomain.setMemberID(memberID);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}

		}

		
		return borrowDomain;
	}

	public void SaveBorrowInfo(BorrowInfoDomain borrowDomain) {

		try {
			conn = dataSource.getConnection();
			String query = "update BorrowInfo set ReturnDate=now() where ISBN=\"" + borrowDomain.getISBN()
			+ "\", memberID=\"" + borrowDomain.getMemberID() + "\", BorrowedDate=" + borrowDomain.getBorrowedDate() + "\"";
			preparedStatement = conn.prepareStatement("");
			resultSet = preparedStatement.executeQuery();

		} catch (Exception e) {
		}

		finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {	}

		}
	}

}
