package com.kevinraupp.api.studydockeraws.repositories;

import com.kevinraupp.api.studydockeraws.data.model.Book;
import com.kevinraupp.api.studydockeraws.data.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
