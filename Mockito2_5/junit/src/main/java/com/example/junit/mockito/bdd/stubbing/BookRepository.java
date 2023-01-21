package com.example.junit.mockito.bdd.stubbing;

import java.util.Collection;
import java.util.List;

public interface BookRepository {

	List<Book> findNewBooks(int days);

}
