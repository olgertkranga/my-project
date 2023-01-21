package com.example.junit.mockito.bdd.behavior_verification;

import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@InjectMocks
	private BookService bookService;
	
	@Mock
	private BookRepository bookRepository;
	
//	@Spy
//	private BookRepository bookRepository;
	
	@Test
	void testUpdatePrice2() {
		Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
		when(bookRepository.findBookById("1234")).thenReturn(book);
		bookService.updatePrice("1234", 500);
		verify(bookRepository).findBookById("1234");
		verify(bookRepository).save(book);;
		verifyNoMoreInteractions(bookRepository);
	}
	
	@Test
	public void test_Given_ABook_When_UpdatePriceIsCalledWithNewPrice_Then_BookPriceIsUpdated() {
		// Given - Arrange
		Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
		//given(bookRepository.findBookById("1234")).willReturn(book);
		
		// When - Act
		bookService.updatePrice("1234", 500);
		when(bookRepository.findBookById("1234")).thenReturn(book);
		
		// Then - Assert/Verify
		//then(bookRepository).should().save(book);
	}
	
}
