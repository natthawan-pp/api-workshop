package th.co.cdgs.train.workshop.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import th.co.cdgs.train.workshop.entity.Department;
import th.co.cdgs.train.workshop.entity.Employee;
import th.co.cdgs.train.workshop.entity.JobTitle;
import th.co.cdgs.train.workshop.query.HrQuery;

@Path("skill")
public class SkillController {
	
	@EJB
	private HrQuery hrQuery;
	

	
}

