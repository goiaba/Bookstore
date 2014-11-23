package edu.luc.fall2014.comp433.project.rest.representation;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

public class BookDescriptionRepresentation extends BaseRepresentation {

	private String description;

	public BookDescriptionRepresentation(Short bookId, String description,
			BookstoreURI uri) {
		super();
		this.setDescription(description);
		addLink(new Link("book", uri.getBookPath(bookId.toString()),
				HttpMethod.GET, MediaType.APPLICATION_JSON));
		addLink(new Link("self", uri.getBookDescriptionPath(bookId.toString()),
				HttpMethod.GET, MediaType.APPLICATION_JSON));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
