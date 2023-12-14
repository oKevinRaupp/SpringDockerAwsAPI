package com.kevinraupp.api.studydockeraws.repositories;

import com.kevinraupp.api.studydockeraws.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}