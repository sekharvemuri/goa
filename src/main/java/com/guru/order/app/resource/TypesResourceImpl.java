package com.guru.order.app.resource;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.data.vo.TypeVO;
import com.guru.order.service.TypesService;

@Component
@Path("types")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class TypesResourceImpl {
	
	@Autowired
	private TypesService typesService;

	@GET
	@Path("/")
	public Response getTypes(@Context ServletContext context) {
		List<TypeVO> types = typesService.getTypes();
		return Response.status(Status.OK).entity(types).build();
	}
	
	@POST
	@Path("/place")
	public Response saveType(TypeVO typeVO) throws Exception {
		typesService.saveType(typeVO);
		return Response.status(Status.OK).build();
	}
}
