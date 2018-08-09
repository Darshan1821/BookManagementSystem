package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Book;
import com.google.gson.Gson;
import com.service.BookService;
import com.util.Constants;
import com.util.ResponseMessage;

@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String source = request.getParameter("action");
		BookService bookService = new BookService();
		
		switch (source) {
		case "viewAllBooks":{	
			try {
				ArrayList<Book> books = bookService.getAllBooks();
				if(books!=null){
					response.setStatus(Constants.getSuccessCode());
					response.getWriter().write(new Gson().toJson(books));
				}else{
					response.setStatus(Constants.getErrorCode());
					ResponseMessage message = new ResponseMessage();
					message.setMessage(Constants.getFetchBookDetailsError());
					response.getWriter().write(new Gson().toJson(message));
				}
			} catch (ClassNotFoundException | SQLException e) {
				response.setStatus(Constants.getExceptionCode());
				ResponseMessage message = new ResponseMessage();
				message.setMessage(Constants.getGeneralError());
				response.getWriter().write(new Gson().toJson(message));
			}
			break;
		}
		case "addBook":{
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String requestJson = "";

			if (bufferedReader != null) {
				requestJson = bufferedReader.readLine();
			}
			
			Book book = new Gson().fromJson(requestJson, Book.class);
			
			try {
				
				long bookId = bookService.addBook(book);
				
				if(bookId!=-1){
					ResponseMessage message = new ResponseMessage();
					message.setMessage(Constants.getBookAddedSuccess() + bookId);
					response.setStatus(Constants.getSuccessCode());
					response.getWriter().write(new Gson().toJson(message));
				}else{
					response.setStatus(Constants.getErrorCode());
					ResponseMessage message = new ResponseMessage();
					message.setMessage(Constants.getBookAddedError());
					response.getWriter().write(new Gson().toJson(message));
				}
			} catch (ClassNotFoundException | SQLException e) {
				response.setStatus(Constants.getExceptionCode());
				ResponseMessage message = new ResponseMessage();
				message.setMessage(Constants.getGeneralError());
				response.getWriter().write(new Gson().toJson(message));
			}
			break;
		}
		case "getBook":{
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String requestJson = "";

			if (bufferedReader != null) {
				requestJson = bufferedReader.readLine();
			}
			
			Book book = new Gson().fromJson(requestJson, Book.class);
			
			try {
				
				Book responseBook = bookService.getBook(book.getBookId());
				
				if(responseBook!=null){
					response.setStatus(Constants.getSuccessCode());
					response.getWriter().write(new Gson().toJson(responseBook));
				}else{
					response.setStatus(Constants.getErrorCode());
					ResponseMessage message = new ResponseMessage();
					message.setMessage(Constants.getGeneralError());
					response.getWriter().write(new Gson().toJson(message));
				}
			} catch (ClassNotFoundException | SQLException e) {
				response.setStatus(Constants.getExceptionCode());
				ResponseMessage message = new ResponseMessage();
				message.setMessage(Constants.getGeneralError());
				response.getWriter().write(new Gson().toJson(message));
			}
			break;
		}
		case "updateBook":{
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String requestJson = "";

			if (bufferedReader != null) {
				requestJson = bufferedReader.readLine();
			}
			
			Book book = new Gson().fromJson(requestJson, Book.class);
			
			try {
				
				boolean success = bookService.updateBook(book);
				
				if(success){
					ResponseMessage message = new ResponseMessage();
					message.setMessage(Constants.getBookUpdateSuccess() + book.getBookId());
					response.setStatus(Constants.getSuccessCode());
					response.getWriter().write(new Gson().toJson(message));
				}else{
					response.setStatus(Constants.getErrorCode());
					ResponseMessage message = new ResponseMessage();
					message.setMessage(Constants.getBookUpdateError());
					response.getWriter().write(new Gson().toJson(message));
				}
			} catch (ClassNotFoundException | SQLException e) {
				response.setStatus(Constants.getExceptionCode());
				ResponseMessage message = new ResponseMessage();
				message.setMessage(Constants.getGeneralError());
				response.getWriter().write(new Gson().toJson(message));
			}
			break;
		}
		case "deleteBook":{
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String requestJson = "";

			if (bufferedReader != null) {
				requestJson = bufferedReader.readLine();
			}
			
			Book book = new Gson().fromJson(requestJson, Book.class);
			
			try {
				
				boolean success = bookService.deleteBook(book.getBookId());
				
				if(success){
					ResponseMessage message = new ResponseMessage();
					message.setMessage(Constants.getBookDeleteSuccess());
					response.setStatus(Constants.getSuccessCode());
					response.getWriter().write(new Gson().toJson(message));
				}else{
					response.setStatus(Constants.getErrorCode());
					ResponseMessage message = new ResponseMessage();
					message.setMessage(Constants.getBookDeleteError());
					response.getWriter().write(new Gson().toJson(message));
				}
			} catch (ClassNotFoundException | SQLException e) {
				response.setStatus(Constants.getExceptionCode());
				ResponseMessage message = new ResponseMessage();
				message.setMessage(Constants.getGeneralError());
				response.getWriter().write(new Gson().toJson(message));
			}
			break;
		}
		default:
			break;
		}
	}
}
