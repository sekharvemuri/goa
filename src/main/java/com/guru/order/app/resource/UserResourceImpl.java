/**
 * 
 */
package com.guru.order.app.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.dto.UserDTO;
import com.guru.order.service.UserService;

/**
 * @author RPILLARISETT
 * 
 */
@Component
@Path("user")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class UserResourceImpl {

	@Autowired
	UserService userService;

	@GET
	@Path("/")
	public Response getUsers() {
		List<UserDTO> list = userService.getUsers();
		return Response.ok(list).build();
	}

	@PUT
	@Path("/add")
	public Response addUser(UserDTO userDTO) {
		userService.addUser(userDTO);
		return Response.ok().build();
	}

	@POST
	@Path("/update")
	public Response updateUser(UserDTO userDTO) {
		userService.updateUser(userDTO);
		return Response.ok().build();
	}

	@DELETE
	@Path("/delete")
	public Response deleteUser(UserDTO userDTO) {
		userService.deleteUser(userDTO);
		return Response.ok().build();
	}
}
