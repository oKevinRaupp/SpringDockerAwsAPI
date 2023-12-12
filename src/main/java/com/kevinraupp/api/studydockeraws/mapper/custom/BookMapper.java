package com.kevinraupp.api.studydockeraws.mapper.custom;

import com.kevinraupp.api.studydockeraws.data.model.Book;
import com.kevinraupp.api.studydockeraws.data.vo.v1.BookVO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookMapper {
    public BookVO convertBookToVo(Book book) {
        BookVO vo = new BookVO();
        vo.setKey(book.getId());
        vo.setAuthor(book.getAuthor());
        vo.setLaunch_date(vo.getLaunch_date());
        vo.setPrice(book.getPrice());
        vo.setTitle(book.getTitle());
        return vo;
    }

    public Book convertVoToBook(BookVO bookVO) {
        Book entity = new Book();
        entity.setId(bookVO.getKey());
        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunch_date(bookVO.getLaunch_date());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());
        return entity;
    }
}
