/**
 * 
 */
package com.guru.order.app.resource;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.dto.CommodityDTO;
import com.guru.order.dto.CommodityFamilyDTO;
import com.guru.order.service.CommodityService;

/**
 * @author RPILLARISETT
 * 
 */
@Component
@Path("commodity")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class CommodityResourceImpl {

	@Autowired
	private CommodityService commodityService;

	/**
	 * http://localhost:8080/<appname>/rest/commodity
	 * 
	 * @return
	 */
	@GET
	@Path("/")
	public Response getCommodities(@Context ServletContext context) {
		List<CommodityDTO> dtos = commodityService.getCommoditiesWithExpiryDates();
		return Response.ok(dtos).build();
	}
	
	@GET
	@Path("/family")
	public Response getCommodityFamilies(@Context ServletContext context) {
		List<CommodityFamilyDTO> dtos = commodityService.getCommodityFamilies();
		return Response.ok(dtos).build();
	}

	/**
	 * http://localhost:8080/<appname>/rest/commodity/add
	 * 
	 * @return
	 */
	@PUT
	@Path("/add")
	public Response addCommodity(CommodityDTO commodityDTO) {
		commodityService.addCommodity(commodityDTO);
		return Response.ok().build();
	}

	/**
	 * http://localhost:8080/<appname>/rest/commodity/update
	 * 
	 * @return
	 */
	@POST
	@Path("/update")
	public Response updateCommodity(CommodityDTO commodityDTO) {
		commodityService.updateCommodity(commodityDTO);
		return Response.ok().build();
	}

	/**
	 * http://localhost:8080/<appname>/rest/commodity/delete
	 * 
	 * @return
	 */
	@DELETE
	@Path("/delete")
	public Response deleteCommodity(CommodityDTO commodityDTO) {
		commodityService.deleteCommodity(commodityDTO);
		return Response.ok().build();
	}

}
