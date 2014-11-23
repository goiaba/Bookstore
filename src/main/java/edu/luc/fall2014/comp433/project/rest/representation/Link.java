package edu.luc.fall2014.comp433.project.rest.representation;


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
