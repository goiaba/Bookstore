/**
 * 
 */
package edu.luc.fall2014.comp433.project.rest.resource.impl;

import java.net.URI;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import edu.luc.fall2014.comp433.project.model.BaseEntity;
import edu.luc.fall2014.comp433.project.rest.resource.BaseService;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * 
 */
public class BaseResourceImpl<I extends Number, E extends BaseEntity<I>>
		implements BaseService<I, E> {

	@Context
	private UriInfo uriInfo;
	
	protected UriInfo getUriInfo() {
		return uriInfo;
	}
	
	protected URI getRequestUri() {
		return uriInfo.getRequestUri();
	}
	
	protected Response created(URI uri, Object entity) {
		return Response.created(uri).entity(entity).build();
	}

	protected Response ok(Object entity) {
		return Response.ok(entity).build();
	}

	protected Response ok() {
		return Response.ok().build();
	}

	protected Response notFound(Object entity) {
		return Response.status(Status.NOT_FOUND).entity(entity).build();
	}

	protected Response notFound() {
		return Response.status(Status.NOT_FOUND).build();
	}

	protected Response internalServerError(Object entity) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(entity)
				.build();
	}

	protected Response internalServerError() {
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
	
	protected Response badRequest() {
		return Response.status(Status.BAD_REQUEST).build();
	}
	
}
