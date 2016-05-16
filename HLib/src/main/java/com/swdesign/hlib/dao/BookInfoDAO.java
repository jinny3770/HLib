package com.swdesign.hlib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.swdesign.hlib.domain.BookDomain;

public class BookInfoDAO {

	DataSource dataSource;
	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public BookInfoDAO() {
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public BookDomain getBookInfo(String ISBN) {
		BookDomain bookDomain = new BookDomain();

		// DB에서 정보 꺼내오는 역할.

		try {
			conn = dataSource.getConnection();
			String query = "select * from BookInfo where =\"" + ISBN + "\"";
			preparedStatement = conn.prepareStatement("");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String bookName = resultSet.getString("BookName");
				Boolean bookState = resultSet.getBoolean("BookState");

				bookDomain.setBookName(bookName);
				bookDomain.setISBN(ISBN);
				bookDomain.setBookState(bookState);

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

		return bookDomain;
	}

	public void saveBookInfo(BookDomain bookDomain) {
		// DB에 book정보 저장

		try {
			conn = dataSource.getConnection();
			String query = "update BookInfo set BookState = \"" + bookDomain.getBookState() + "\" where ISBN = \""
					+ bookDomain.getISBN() + "\"";
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
