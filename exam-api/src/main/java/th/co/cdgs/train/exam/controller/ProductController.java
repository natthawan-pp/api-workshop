package th.co.cdgs.train.exam.controller;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import th.co.cdgs.train.exam.query.OnlineShopQuery;

@Path("product")
public class ProductController {
	
	@EJB
	private OnlineShopQuery onlineShopQuery;
	
	@GET
	@Path("/queryProductByCategoryCode")
	@Produces(MediaType.APPLICATION_JSON )
	public Response queryProductByCategoryCode (
			@QueryParam("categoryCode") String categoryCode) {
	    return Response.ok().entity(onlineShopQuery.queryProductByCategoryCode(categoryCode)).build();
	}
	
	
	

}
