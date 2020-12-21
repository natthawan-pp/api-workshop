package th.co.cdgs.train.exam.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import th.co.cdgs.train.exam.entity.Category;
import th.co.cdgs.train.exam.query.OnlineShopQuery;


@Path("category")
public class CategoryController {
	
	@EJB
	private OnlineShopQuery onlineShopQuery;
	
	@GET
	@Path("/queryCategory")
	@Produces(MediaType.APPLICATION_JSON )
	public Response queryCategory() {
		List<Category> departments = onlineShopQuery.queryCategory();
		if (departments == null) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(departments).build();
	}
	
}

