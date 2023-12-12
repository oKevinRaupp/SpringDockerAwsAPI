package com.kevinraupp.api.studydockeraws.unittests;

import com.kevinraupp.api.studydockeraws.data.model.Book;
import com.kevinraupp.api.studydockeraws.data.vo.v1.BookVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor("Author Test" + number);
        book.setLaunch_date(new Date());
        book.setPrice(100 + number);
        book.setId(number.longValue());
        book.setTitle("Title Test" + number);
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setAuthor("Author Test" + number);
        book.setLaunch_date(new Date());
        book.setPrice(100 + number);
        book.setKey(number.longValue());
        book.setTitle("Title Test" + number);
        return book;
    }
}
