package com.guru.order.app.resource;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.dto.OrderDTO;
import com.guru.order.service.GroupService;

@Component
@Path("groups")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class GroupResourceImpl {
	
	@Autowired
	private GroupService groupService;

	/**
	 * http://localhost:8080/<appname>/rest/groups
	 * 
	 * @return
	 */
	@GET
	@Path("/")
	public Response groups(@Context ServletContext context) {
		System.out.println("returning Groups.");
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setGroups(groupService.getGroups());
		return Response.status(Status.OK).entity(groupService.getGroups()).build();
	}
	
}
