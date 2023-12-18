package br.com.restfull.unittests.mapper.mocks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.restful.model.Book;
import br.com.restful.vo.v1.BookValueObject;

public class MockBook {

	public Book mockEntity() {
		return mockEntity(0);
	}

	public BookValueObject mockVO() {
		return mockVO(0);
	}

	public List<Book> mockEntityList() {
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 14; i++) {
			books.add(mockEntity(i));
		}
		return books;
	}

	public List<BookValueObject> mockVOList() {
		List<BookValueObject> books = new ArrayList<>();
		for (int i = 0; i < 14; i++) {
			books.add(mockVO(i));
		}
		return books;
	}

	public Book mockEntity(Integer number) {
		Book book = new Book();
		try {
			book.setId(number.longValue());
			book.setAuthor("Author Test " + number);
			book.setTitle("Title Test " + number);
			book.setPrice(number.doubleValue());
			Date targetDate = null;
			targetDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-17");
			book.setLaunchDate(targetDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return book;
	}

	public BookValueObject mockVO(Integer number) {
		BookValueObject book = new BookValueObject();
		try {
			book.setKey(number.longValue());
			Date targetDate = null;
			targetDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-17");
			book.setAuthor("Author Test " + number);
			book.setLaunchDate(targetDate);
			book.setPrice(number.doubleValue());
			book.setTitle("Title Test " + number);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return book;
	}

}
