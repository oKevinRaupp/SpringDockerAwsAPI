package com.kevinraupp.api.studydockeraws.services;

import com.kevinraupp.api.studydockeraws.controller.PersonController;
import com.kevinraupp.api.studydockeraws.data.vo.v1.BookVO;
import com.kevinraupp.api.studydockeraws.exceptions.RequiredObjectIsNullException;
import com.kevinraupp.api.studydockeraws.exceptions.ResourceNotFoundException;
import com.kevinraupp.api.studydockeraws.mapper.DozerMapper;
import com.kevinraupp.api.studydockeraws.mapper.custom.BookMapper;
import com.kevinraupp.api.studydockeraws.model.Book;
import com.kevinraupp.api.studydockeraws.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
    @Autowired
    BookRepository repository;
    @Autowired
    BookMapper mapper;
    private Logger logger = Logger.getLogger(BookServices.class.getName());

    public BookVO findById(Long id) {
        logger.info("Finding one book!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify! "));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findByID(id)).withSelfRel());
        return vo;
    }

    public List<BookVO> findAll() {
        logger.info("Finding all books!");
        var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
        books.stream().forEach(b -> b.add(linkTo(methodOn(PersonController.class).findByID(b.getKey())).withSelfRel()));
        return books;
    }

    public BookVO create(BookVO bookVO) {
        if (bookVO == null) {
            logger.info("Trying to create a null object!");
            throw new RequiredObjectIsNullException();
        }
        logger.info("Creating a new book!");

        var entity = DozerMapper.parseObject(bookVO, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findByID(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO bookVO) {
        if (bookVO == null) {
            logger.info("Trying to update a null object!");
            throw new RequiredObjectIsNullException();
        }
        logger.info("Updating one book!");

        var entity = repository.findById(bookVO.getKey()).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify!"));
        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunch_date(bookVO.getLaunch_date());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());

        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findByID(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one book!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify!"));
        repository.delete(entity);
    }
}