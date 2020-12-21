package th.co.cdgs.train.exam.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import th.co.cdgs.train.exam.entity.Category;
import th.co.cdgs.train.exam.entity.Customer;
import th.co.cdgs.train.exam.query.OnlineShopQuery;

@Path("customer")
public class CustomerController {
	
	@EJB
	private OnlineShopQuery onlineShopQuery;
	
	@GET
	@Path("{customerId}")
	@Produces(MediaType.APPLICATION_JSON )
	public Response queryCustomerById(@PathParam("customerId") String customerId) {
	    return Response.ok().entity(onlineShopQuery.queryCustomerById(customerId)).build();
	}
}