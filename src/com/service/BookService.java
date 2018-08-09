package com.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.beans.Book;
import com.dao.BookDao;

public class BookService {

	BookDao bookDao = new BookDao();
	
	public ArrayList<Book> getAllBooks() throws ClassNotFoundException, SQLException{
		return bookDao.getAllBooks();
	}
	
	public long addBook(Book book) throws ClassNotFoundException, SQLException{
		return bookDao.addBook(book);
	}
	
	public Book getBook(long bookId) throws ClassNotFoundException, SQLException{
		return bookDao.getBook(bookId);
	}
	
	public boolean updateBook(Book book) throws ClassNotFoundException, SQLException{
		return bookDao.updateBook(book);
	}
	
	public boolean deleteBook(long bookId) throws ClassNotFoundException, SQLException{
		return bookDao.deleteBook(bookId);
	}
}
