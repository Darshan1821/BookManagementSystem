package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.beans.Book;
import com.util.DatabaseUtil;

public class BookDao {

	public ArrayList<Book> getAllBooks() throws ClassNotFoundException, SQLException{
		
		Connection connection = DatabaseUtil.getConnection();
		ArrayList<Book> books = new ArrayList<>();
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOK_TJA19");
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()){
			Book book = new Book();
			book.setBookId(resultSet.getLong(1));
			book.setBookName(resultSet.getString(2));
			book.setBookAuthor(resultSet.getString(3));
			book.setBookCategory(resultSet.getString(4));
			book.setBookPages(resultSet.getInt(5));
			book.setBookLanguage(resultSet.getString(6));
			
			books.add(book);
		}
		
		DatabaseUtil.closeSet(resultSet);
		DatabaseUtil.closeStatement(preparedStatement);
		DatabaseUtil.closeConnection(connection);
		return books;
	}
	
	public long addBook(Book book) throws ClassNotFoundException, SQLException{
		
		Connection connection = DatabaseUtil.getConnection();
		long bookId = -1;
		
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO BOOK_TJA19 VALUES(BOOK_SEQ.nextval,?,?,?,?,?)");
		preparedStatement.setString(1, book.getBookName());
		preparedStatement.setString(2, book.getBookAuthor());
		preparedStatement.setString(3, book.getBookCategory());
		preparedStatement.setInt(4, book.getBookPages());
		preparedStatement.setString(5, book.getBookLanguage());
		
		if(preparedStatement.executeUpdate() > 0){
			preparedStatement = connection.prepareStatement("SELECT MAX(BOOK_ID) FROM BOOK_TJA19");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				bookId = resultSet.getLong(1);
			}
			DatabaseUtil.closeSet(resultSet);
		}
		
		DatabaseUtil.closeStatement(preparedStatement);
		DatabaseUtil.closeConnection(connection);
		return bookId;
	}
	
	public Book getBook(long bookId) throws ClassNotFoundException, SQLException {
		
		Connection connection = DatabaseUtil.getConnection();
		Book book = null;
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOK_TJA19 WHERE BOOK_ID = ?");
		preparedStatement.setLong(1, bookId);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()){
			book = new Book();
			book.setBookId(resultSet.getLong(1));
			book.setBookName(resultSet.getString(2));
			book.setBookAuthor(resultSet.getString(3));
			book.setBookCategory(resultSet.getString(4));
			book.setBookPages(resultSet.getInt(5));
			book.setBookLanguage(resultSet.getString(6));
		}
		
		DatabaseUtil.closeSet(resultSet);
		DatabaseUtil.closeStatement(preparedStatement);
		DatabaseUtil.closeConnection(connection);
		
		return book;
	}
	
	public boolean updateBook(Book book) throws ClassNotFoundException, SQLException {
		
		Connection connection = DatabaseUtil.getConnection();
		boolean success = false;
		
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE BOOK_TJA19 SET BOOK_NAME = ?, BOOK_AUTHOR = ?, BOOK_CATEGORY = ?, BOOK_PAGES = ?, BOOK_LANGUAGE = ? WHERE BOOK_ID = ?");
		preparedStatement.setString(1, book.getBookName());
		preparedStatement.setString(2, book.getBookAuthor());
		preparedStatement.setString(3, book.getBookCategory());
		preparedStatement.setInt(4, book.getBookPages());
		preparedStatement.setString(5, book.getBookLanguage());
		preparedStatement.setLong(6, book.getBookId());
		
		if(preparedStatement.executeUpdate() > 0 ){
			success = true;
		}
		
		DatabaseUtil.closeStatement(preparedStatement);
		DatabaseUtil.closeConnection(connection);
		return success;
	}
	
	public boolean deleteBook(long bookId) throws ClassNotFoundException, SQLException {
		
		Connection connection = DatabaseUtil.getConnection();
		boolean success = false;
		
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM BOOK_TJA19 WHERE BOOK_ID = ?");
		preparedStatement.setLong(1, bookId);
		
		if(preparedStatement.executeUpdate() > 0 ){
			success = true;
		}
		
		DatabaseUtil.closeStatement(preparedStatement);
		DatabaseUtil.closeConnection(connection);
		return success;
	}
}
