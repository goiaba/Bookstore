package edu.luc.fall2014.comp433.project.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

public abstract class BaseEntity<I extends Number> implements Serializable  {

	private static final long serialVersionUID = 1L;

	public abstract I getId();
	public abstract void setId(I id);
	
	@XmlTransient
	@JsonIgnore
	public boolean isPersisted() {
		return (null != getId());
	}
	
}
