package com.example.junit.mockito.argument_captor;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@InjectMocks
	private BookService bookService;
	
	@Mock
	private BookRepository bookRepository;
	
	@Captor
	private ArgumentCaptor<Book> bookArgumentCaptor;
	
	@Test
	public void testSaveBook() {
		BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
		ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
		bookService.addBook(bookRequest);
		verify(bookRepository, times(1)).save(bookArgumentCaptor.capture());
		Book book = bookArgumentCaptor.getValue();
		assertEquals("Mockito In Action", book.getTitle());
	}
		
}
