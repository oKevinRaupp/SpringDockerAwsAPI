package com.kevinraupp.api.studydockeraws.services;

import com.kevinraupp.api.studydockeraws.controller.BookController;
import com.kevinraupp.api.studydockeraws.data.vo.v1.BookVO;
import com.kevinraupp.api.studydockeraws.exceptions.RequiredObjectIsNullException;
import com.kevinraupp.api.studydockeraws.exceptions.ResourceNotFoundException;
import com.kevinraupp.api.studydockeraws.mapper.DozerMapper;
import com.kevinraupp.api.studydockeraws.mapper.custom.BookMapper;
import com.kevinraupp.api.studydockeraws.model.Book;
import com.kevinraupp.api.studydockeraws.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
    private Logger logger = Logger.getLogger(BookServices.class.getName());
    @Autowired
    BookRepository repository;
    @Autowired
    BookMapper mapper;
    @Autowired
    PagedResourcesAssembler<BookVO> assembler;

    public BookVO findById(Long id) {
        logger.info("Finding one book!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify! "));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findByID(id)).withSelfRel());
        return vo;
    }

    public PagedModel<EntityModel<BookVO>> findAll(Pageable pageable) {
        logger.info("Finding all books!" + pageable.toString());

        var bookPage = repository.findAll(pageable);

        var bookVoPage = bookPage.map(p -> DozerMapper.parseObject(repository.save(p), BookVO.class));

        bookVoPage.map(p -> p.add(linkTo(methodOn(BookController.class).findByID(p.getKey())).withSelfRel()));

        Link link = linkTo(methodOn(BookController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();

        return assembler.toModel(bookVoPage, link);
    }

    public BookVO create(BookVO bookVO) {
        if (bookVO == null) {
            logger.info("Trying to create a null object!");
            throw new RequiredObjectIsNullException();
        }
        logger.info("Creating a new book!");

        var entity = DozerMapper.parseObject(bookVO, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findByID(vo.getKey())).withSelfRel());
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
        vo.add(linkTo(methodOn(BookController.class).findByID(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one book!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found! Verify!"));
        repository.delete(entity);
    }
}