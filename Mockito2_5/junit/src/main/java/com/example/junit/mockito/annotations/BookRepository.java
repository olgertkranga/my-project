package com.example.junit.mockito.annotations;

import java.util.List;

public interface BookRepository {

	List<Book> findNewBooks(int days);

}
