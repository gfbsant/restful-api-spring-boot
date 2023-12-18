package br.com.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restful.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
