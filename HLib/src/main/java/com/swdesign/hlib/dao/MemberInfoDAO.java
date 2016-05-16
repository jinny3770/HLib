package com.swdesign.hlib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.swdesign.hlib.domain.MemberDomain;

public class MemberInfoDAO {
	
	DataSource dataSource;
	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	
	public MemberInfoDAO() {
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MemberDomain getMember(String memberID) {
		
		MemberDomain memberDomain = new MemberDomain();
		
		try {
			conn = dataSource.getConnection();
			String query = "select * from MemberInfo where =\"" + memberID + "\"";
			preparedStatement = conn.prepareStatement("");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int allPoint = resultSet.getInt("AllPoint");
				int monthPoint = resultSet.getInt("MonthPoint");
				int borrowedCount = resultSet.getInt("BorrowedBookCount");
				int borrowableCount = resultSet.getInt("BorowableBookCount");
				int borrowableTerm = resultSet.getInt("BorrowableTerm");
				
				memberDomain.setAllPoint(allPoint);
				memberDomain.setMonthPoint(monthPoint);
				memberDomain.setBorrowableBookCount(borrowableCount);
				memberDomain.setBorrowedBookCount(borrowedCount);
				memberDomain.setBorrowableTerm(borrowableTerm);
			}

		} catch (Exception e) {
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

		return memberDomain;
	}
	
	public void saveMember(MemberDomain memberDomain) {
		
	}
}
