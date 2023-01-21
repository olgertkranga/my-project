package com.example.junit.mockito.verification;

import java.util.List;

public interface BookRepository {
	void save(Book book);

	Book findBookById(String bookId);
}
