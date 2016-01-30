/**
 * 
 */
package com.guru.order.app.resource.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * @author RPILLARISETT
 * 
 */
@Path("/")
public interface OrderResource {

	@GET
	@Path("/check")
	public Response checkApp(@PathParam("myname") String myname);
}
