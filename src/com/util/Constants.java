package com.util;

public class Constants {

	private final static String GENERAL_ERROR = "Something went wrong! Please try again!";
	private final static String FETCH_BOOK_DETAILS_ERROR = "Error while fetching book details !";
	private final static String BOOK_ADDED_SUCCESS = "Book added successfully with id: ";
	private final static String BOOK_ADDED_ERROR = "Book add operation failed !";
	private final static String BOOK_UPDATE_SUCCESS = "Book updated successfully with id: ";
	private final static String BOOK_UPDATE_ERROR = "Book update operation failed !";
	private final static String BOOK_DELETE_SUCCESS = "Book deleted successfully !";
	private final static String BOOK_DELETE_ERROR = "Book delete operation failed !";

	private final static int SUCCESS_CODE = 200;
	private final static int ERROR_CODE = 400;
	private final static int EXCEPTION_CODE = 503;

	public static String getGeneralError() {
		return GENERAL_ERROR;
	}

	public static String getFetchBookDetailsError() {
		return FETCH_BOOK_DETAILS_ERROR;
	}

	public static int getSuccessCode() {
		return SUCCESS_CODE;
	}

	public static int getErrorCode() {
		return ERROR_CODE;
	}

	public static int getExceptionCode() {
		return EXCEPTION_CODE;
	}

	public static String getBookAddedSuccess() {
		return BOOK_ADDED_SUCCESS;
	}

	public static String getBookAddedError() {
		return BOOK_ADDED_ERROR;
	}

	public static String getBookUpdateSuccess() {
		return BOOK_UPDATE_SUCCESS;
	}

	public static String getBookUpdateError() {
		return BOOK_UPDATE_ERROR;
	}

	public static String getBookDeleteSuccess() {
		return BOOK_DELETE_SUCCESS;
	}

	public static String getBookDeleteError() {
		return BOOK_DELETE_ERROR;
	}
}
