package edu.luc.fall2014.comp433.project.rest.representation;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import edu.luc.fall2014.comp433.project.model.BaseEntity;

@XmlRootElement
public abstract class BaseRepresentation {

	private BookstoreURI uri;
	private Link selfLink;
	private List<Link> links;

	public BaseRepresentation() {
		this.links = new ArrayList<Link>();
	}

	/**
	 * Creates a list of representation instances of some model entity
	 * 
	 * All classes that inherits from <code>BaseRepresentation</code> must have
	 * constructors as:
	 * 
	 * XRepresentation(BaseRepresentation, Class
	 * 
	 * @param representationClass
	 *            the representation type to be constructed
	 * @param entityClass
	 *            the entity class that serves as source
	 * @param list
	 *            the list of model entities to be transformed
	 * @return a list of representation instances of the entities
	 */
	@JsonIgnore
	@XmlTransient
	public static <T extends BaseRepresentation, L extends BaseEntity<?>> List<T> fromModelList(
			Class<T> representationClass, Class<L> entityClass, List<L> list,
			BookstoreURI uri) {
		List<T> resultList = new ArrayList<T>();
		if (null != list) {

			for (L element : list) {
				try {
					T elRep = representationClass.getConstructor(entityClass,
							BookstoreURI.class).newInstance(element, uri);
					resultList.add(elRep);
				} catch (Exception e) {
					throw new RuntimeException(
							"Error when trying to construct a representation instance of type "
									+ representationClass.getCanonicalName(), e);
				}
			}
		}
		return resultList;
	}
	

	@JsonProperty
	protected List<Link> getLinks() {
		return links;
	}
	
	public void addLink(Link link) {
		if ("self".equals(link.getRel())) 
				this.selfLink = link;
		this.links.add(link);
	}
	
	@JsonIgnore
	@XmlTransient
	public void setSelfLink(Link selfLink) {
		this.selfLink = selfLink;
	}

	@JsonIgnore
	@XmlTransient
	public Link getSelfLink() {
		return this.selfLink;
	}

	@JsonIgnore
	@XmlTransient
	public URI getSelfLinkUri() {
		if (null != selfLink)
			return URI.create(selfLink.getHref());
		return null;
	}
	
	@JsonIgnore
	@XmlTransient
	public BookstoreURI getUri() {
		return uri;
	}

	public void setUri(BookstoreURI uri) {
		this.uri = uri;
	}

}
