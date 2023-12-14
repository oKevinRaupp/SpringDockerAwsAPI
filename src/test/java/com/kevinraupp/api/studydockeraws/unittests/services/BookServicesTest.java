package com.kevinraupp.api.studydockeraws.unittests.services;

import com.kevinraupp.api.studydockeraws.data.model.Book;
import com.kevinraupp.api.studydockeraws.data.vo.v1.BookVO;
import com.kevinraupp.api.studydockeraws.exceptions.RequiredObjectIsNullException;
import com.kevinraupp.api.studydockeraws.repositories.BookRepository;
import com.kevinraupp.api.studydockeraws.services.BookServices;
import com.kevinraupp.api.studydockeraws.unittests.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BookServicesTest {

    MockBook input;
    @InjectMocks
    private BookServices services;
    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = services.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));

        assertEquals("Author Test1", result.getAuthor());
//        assertEquals(new Date(),result.getLaunch_date());
        assertEquals(101, result.getPrice());
        assertEquals(1L, result.getKey());
        assertEquals("Title Test1", result.getTitle());

    }

    @Test
    void findAll() {
        List<Book> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var books = services.findAll();

        assertNotNull(books);
        assertEquals(14, books.size());

        var firstBook = books.get(1);
        assertNotNull(firstBook);
        assertNotNull(firstBook.getKey());
        assertNotNull(firstBook.getLinks());

        assertTrue(firstBook.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));

        assertEquals("Author Test1", firstBook.getAuthor());
//        assertEquals(new Date(),firstBook.getLaunch_date());
        assertEquals(101, firstBook.getPrice());
        assertEquals(1L, firstBook.getKey());
        assertEquals("Title Test1", firstBook.getTitle());

        var secondBook = books.get(2);
        assertNotNull(secondBook);
        assertNotNull(secondBook.getKey());
        assertNotNull(secondBook.getLinks());

        assertTrue(secondBook.toString().contains("links: [</api/person/v1/2>;rel=\"self\"]"));

        assertEquals("Author Test2", secondBook.getAuthor());
//        assertEquals(new Date(),secondBook.getLaunch_date());
        assertEquals(102, secondBook.getPrice());
        assertEquals(2L, secondBook.getKey());
        assertEquals("Title Test2", secondBook.getTitle());

        var bookTen = books.get(10);
        assertNotNull(bookTen);
        assertNotNull(bookTen.getKey());
        assertNotNull(bookTen.getLinks());

        assertTrue(bookTen.toString().contains("links: [</api/person/v1/10>;rel=\"self\"]"));

        assertEquals("Author Test10", bookTen.getAuthor());
//        assertEquals(new Date(),bookTen.getLaunch_date());
        assertEquals(110, bookTen.getPrice());
        assertEquals(10L, bookTen.getKey());
        assertEquals("Title Test10", bookTen.getTitle());

    }

    @Test
    void create() {
        Book entity = input.mockEntity(1);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = services.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));

        assertEquals("Author Test1", result.getAuthor());
//        assertEquals(new Date(),result.getLaunch_date());
        assertEquals(101, result.getPrice());
        assertEquals(1L, result.getKey());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void createWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            services.create(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            services.update(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = services.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));

        assertEquals("Author Test1", result.getAuthor());
//        assertEquals(new Date(),result.getLaunch_date());
        assertEquals(101, result.getPrice());
        assertEquals(1L, result.getKey());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void delete() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        services.delete(1L);
    }
}