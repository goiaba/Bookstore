package edu.luc.fall2014.comp433.project.service.workflow;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import edu.luc.fall2014.comp433.project.model.Book;

@RequestScoped
public interface BookActivity extends BaseActivity<Short, Book> {

	Book searchById(Short id);

	List<Book> genericSearch(List<Short> ids, String title, String author,
			BigDecimal minPrice, BigDecimal maxPrice);

	List<Book> findBookByIds(List<Short> bookIdList);

	String retrieveBookDescription(Short id);
	
}
