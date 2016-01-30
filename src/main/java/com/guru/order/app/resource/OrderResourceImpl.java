/**
 * 
 */
package com.guru.order.app.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.dto.ExpiryDateDTO;
import com.guru.order.dto.GroupDTO;
import com.guru.order.dto.OrderConfirmationDTO;
import com.guru.order.dto.OrderDTO;
import com.guru.order.service.MetaDataLoader;
import com.guru.order.service.OrderService;

/**
 * @author RPILLARISETT
 * 
 */
@Component
@Path("order")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML })
public class OrderResourceImpl {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * http://localhost:8080/<appname>/rest/order/place/orderone
	 * 
	 * @param order
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@GET
	@Path("/")
	public Response order(@Context ServletContext context) {
		System.out.println("in Order/");
		List<ExpiryDateDTO> expiryDatesList = (List<ExpiryDateDTO>) context
				.getAttribute(MetaDataLoader.EXPIRY_DATES);
		if (expiryDatesList == null) {
			MetaDataLoader.loadData(context);
			expiryDatesList = (List<ExpiryDateDTO>) context
					.getAttribute(MetaDataLoader.EXPIRY_DATES);
		}

		OrderDTO orderDTO = this.orderService.getNewOrder();
		orderDTO.setExpiryDates(expiryDatesList);
		return Response.status(Status.OK).entity(orderDTO).build();
	}

	@POST
	@Path("/place")
	public Response placeOrderDto(OrderDTO order) {
		this.orderService.saveOrder(order);
		return Response.status(Status.OK).build();
	}

	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadOrderExecution(MultipartFormDataInput input) throws Exception {
		String fileName = "";
		Map<String, List<InputPart>> formParts = input.getFormDataMap();
		List<InputPart> inPart = formParts.get("file"); // input field name
		for (InputPart inputPart : inPart) {
			try {
				MultivaluedMap<String, String> headers = inputPart.getHeaders();
				fileName = parseFileName(headers);
				
				if (!fileName.endsWith(".csv")) {
					throw new Exception("Please upload CSV file.");
				}

				// Handle the body of that part with an InputStream
				InputStream istream = inputPart
						.getBody(InputStream.class, null);
				Reader reader = new InputStreamReader(istream);
				orderService.saveOrderConfirmation(reader);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		String output = "File saved to server location : " + fileName;
		return Response.status(200).entity(output).build();
	}

	private String parseFileName(MultivaluedMap<String, String> headers) {
		String[] contentDispositionHeader = headers.getFirst(
				"Content-Disposition").split(";");
		for (String name : contentDispositionHeader) {
			if ((name.trim().startsWith("filename"))) {
				String[] tmp = name.split("=");
				String fileName = tmp[1].trim().replaceAll("\"", "");
				return fileName;
			}
		}
		return "";
	}
	
	@GET
	@Path("/executed")
	@Produces("text/html")
	public void getExecutedOrders(@Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException, ServletException {
		List<GroupDTO> ordersList = orderService.getCurrentOrders();
		req.setAttribute("ordersList", ordersList);
		req.getRequestDispatcher("/WEB-INF/jsps/ExecutedOrders.jsp").forward(req, resp);
	}
	
	@POST
	@Path("/executed")
	@Produces(MediaType.TEXT_HTML)
	public Response saveExecutedOrders(OrderDTO orderDTO) throws IOException, ServletException {
		String msg = "success";
		try {
			orderService.saveExecutedOrderByGroupName(orderDTO.getExecutionsList());
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return Response.status(Status.OK).entity(msg).build();
	}
}
