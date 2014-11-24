package edu.luc.fall2014.comp433.project.rest.representation;

import javax.ws.rs.core.MediaType;

public class Link {

	private String rel;
	private String href;
	private String method;
	private String mediaType;

	public Link(String rel, String href, String method, String mediaType) {
		this.rel = rel;
		this.href = href;
		this.method = method;
		this.mediaType = mediaType;
	}

	/**
	 * A constructor with a default mediaType set to APPLICATION_JSON
	 * 
	 * @param rel
	 * @param href
	 * @param method
	 */
	public Link(String rel, String href, String method) {
		this.rel = rel;
		this.href = href;
		this.method = method;
		this.mediaType = MediaType.APPLICATION_JSON;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

}
