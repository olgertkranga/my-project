package com.example.junit.mockito.exception_handling;

public class DatabaseReadException extends RuntimeException {
	public DatabaseReadException(String message) {
		super(message);		
	}
}
