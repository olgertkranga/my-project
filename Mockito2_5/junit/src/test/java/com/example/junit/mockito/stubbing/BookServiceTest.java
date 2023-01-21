package com.example.junit.mockito.stubbing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
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
	
	@Test
	public void testCalculateTotalCostOfBooks() {
		List<String> bookIds = new ArrayList<>();
		bookIds.add("1234");
		bookIds.add("1234");
		
		Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book("1235", "JUnit In Action", 400, LocalDate.now());
		
		when(bookRepository.findBookByBookId("1234"))
			.thenReturn(book1)
			.thenReturn(book1);
		//when(bookRepository.findBookByBookId("1234")).thenReturn(book1);
		
		//doReturn(book1).when(bookRepository).findBookByBookId("1234");
		//doReturn(book2).when(bookRepository).findBookByBookId("1235");
		
		int actualCost = bookService.calculateTotalCost(bookIds);
		assertEquals(1000, actualCost);
	}
	
	@Test
	public void testSaveBook() {
		Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
		doNothing().when(bookRepository).save(book1); // ==
		bookService.addBook(book1);
	}
	
	@Test
	public void testSaveBookWithBookRequest() {
		BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
		Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
		doNothing().when(bookRepository).save(book); 
		bookService.addBook(bookRequest);
	}

}
