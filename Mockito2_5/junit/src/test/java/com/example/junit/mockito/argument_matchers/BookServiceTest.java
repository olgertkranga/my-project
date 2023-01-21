package com.example.junit.mockito.argument_matchers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@InjectMocks
	private BookService bookService;
	
	@Mock
	private BookRepository bookRepository;
	
//	@Spy
//	private BookRepository bookRepository;
	
	/**
	 * 
	 */
	@Test
	void testUpdatePrice2() {
		Book book1 = new Book("1234", "Mockito In Action", 600, LocalDate.now());
		Book book2 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
		when(bookRepository.findBookById(any(String.class))).thenReturn(book1);
		bookService.updatePrice("abc", 500);
		verify(bookRepository).save(book2);
	}
	
	/***
	 * 
	 */
	@Test
	public void testInvalidUseOfArgumentMatchers() {
		Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
		when(bookRepository.findBookByTitleAndPublishedDate(eq("Mockito In Action"), any())).thenReturn(book);
		Book actualBook = bookService.getBookByTitleAndPublishedDate(eq("Mockito In Action"), any());
		assertEquals("Mockito In Action", actualBook.getTitle());
	}
	
	@Test
	public void testSpecificTypeOfArgumentMatchers() {
		Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
		when(bookRepository.findBookByTitleAndPriceAndIsDigital(anyString(), anyInt(), anyBoolean())).thenReturn(book);
		Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action", 600, true);
		assertEquals("Mockito In Action", actualBook.getTitle());
	}
	
	//Argment Matchers should be provided for all arguments
	// Argument Matchers cant be used outside stubbing/verification
	
	@Test
	void testCollectionTypeArgumentMatchers() {
		List<Book> books = new ArrayList<>();
		Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
		books.add(book);
		bookService.addBooks(books);
		verify(bookRepository).saveAll(anyList()); // anySet, anyMap, anyCollection
	}
	
	@Test
	public void testStringTypeArgumentMatchers() {
		Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
		when(bookRepository.findBookByTitleAndPriceAndIsDigital(contains("JUnit"), anyInt(), anyBoolean())).thenReturn(book);
		Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("JUnit", 600, true);
		assertEquals("Mockito In Action", actualBook.getTitle());
	}
	
}
