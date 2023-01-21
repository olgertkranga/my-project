package com.example.junit.mockito.argument_captor;

import java.util.List;

public interface BookRepository {
	void save(Book book);

	Book findBookById(String bookId);
}
