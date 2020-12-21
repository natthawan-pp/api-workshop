package th.co.cdgs.train.exam.controller;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import th.co.cdgs.train.exam.business.OnlineShopBusiness;
import th.co.cdgs.train.exam.entity.OrderMaster;
import th.co.cdgs.train.exam.query.OnlineShopQuery;

@Path("order")
public class OrderController {

	@EJB
	private OnlineShopQuery onlineShopQuery;
	
	@EJB
	private OnlineShopBusiness onlineShopBusiness;
	
	@GET
	@Path("/queryPrintOrder/{customerId}")
	@Produces(MediaType.APPLICATION_JSON )
	public Response queryPrintOrder(@PathParam("customerId") String customerId) {
	    return Response.ok().entity(onlineShopQuery.queryPrintOrder(customerId)).build();
	}
	
	@POST
	@Path("/makeOrder")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public Response makeOrder(OrderMaster ordermaster) {
		return Response.status(Status.CREATED).entity(onlineShopBusiness.makeOrder(ordermaster)).build();
	}

	
}
