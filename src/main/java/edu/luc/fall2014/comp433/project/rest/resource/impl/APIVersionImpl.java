/**
 * 
 */
package edu.luc.fall2014.comp433.project.rest.resource.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
@Path("/")
public class APIVersionImpl {

	private static final String apiVersion = "bookStoreApi-v0.1";

	@GET
	@Produces({MediaType.TEXT_PLAIN})
	public Response restfulVersion() {
		return Response.ok(apiVersion).build();
	}
}
