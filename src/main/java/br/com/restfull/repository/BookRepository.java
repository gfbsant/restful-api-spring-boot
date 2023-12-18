package br.com.restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restfull.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
