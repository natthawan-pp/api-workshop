package th.co.cdgs.train.workshop.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import th.co.cdgs.train.workshop.entity.Department;
import th.co.cdgs.train.workshop.entity.Employee;
import th.co.cdgs.train.workshop.entity.JobTitle;
import th.co.cdgs.train.workshop.query.HrQuery;

@Path("department")
public class DepartmentController {
	
	@EJB
	private HrQuery hrQuery;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON )
	public Response queryDepartment() {
		List<Department> departments = hrQuery.queryDepartment();
		if (departments == null) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(departments).build();
	}
	
}

