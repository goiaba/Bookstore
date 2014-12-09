package edu.luc.fall2014.comp433.project.rest.representation;

import java.math.BigDecimal;

import javax.ws.rs.HttpMethod;
import javax.xml.bind.annotation.XmlRootElement;

import edu.luc.fall2014.comp433.project.model.Book;

@XmlRootElement
public class BookRepresentation extends BaseRepresentation {

	private Short id;
	private String isbn;
	private String title;
	private String author;
	private BigDecimal price;

	public BookRepresentation(Book entity, BookstoreURI uri) {
		super();
		populateFields(entity, uri);
	}

	private void populateFields(Book entity, BookstoreURI uri) {
		if (null != entity) {
			this.setAuthor(entity.getAuthor());
			this.setId(entity.getId());
			this.setIsbn(entity.getIsbn());
			this.setPrice(entity.getPrice());
			this.setTitle(entity.getTitle());
			createLinks(uri);
		}
	}

	public void createLinks(BookstoreURI uri) {
		addLink(new Link("self", uri.getBookPath(getId().toString()),
				HttpMethod.GET));
		addLink(new Link("description", uri.getBookDescriptionPath(getId()
				.toString()), HttpMethod.GET));
		addLink(new Link("buy", uri.getOrderPath(), HttpMethod.POST));
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
