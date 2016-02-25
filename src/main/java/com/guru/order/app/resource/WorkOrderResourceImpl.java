package com.guru.order.app.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.service.impl.WorkOrderServiceImpl;

@Component
@Path("workOrder")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML })
public class WorkOrderResourceImpl {
	
	@Autowired
	private WorkOrderServiceImpl workOrderService;

	@POST
	@Path("/uploadWorkOrder")
	@Consumes("multipart/form-data")
	public Response uploadWorkOrder(MultipartFormDataInput input) throws Exception {
		String fileName = "";
		Map<String, List<InputPart>> formParts = input.getFormDataMap();
		List<InputPart> inPart = formParts.get("file"); // input field name
		for (InputPart inputPart : inPart) {
			try {
				MultivaluedMap<String, String> headers = inputPart.getHeaders();
				fileName = parseFileName(headers);
				
				// Handle the body of that part with an InputStream
				InputStream istream = inputPart
						.getBody(InputStream.class, null);
//				FileInputStream fis = inputPart.getBody(FileInputStream.class, null);
				workOrderService.saveWorkOrders(istream);
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
	
}
