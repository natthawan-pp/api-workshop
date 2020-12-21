package th.co.cdgs.train.workshop.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import th.co.cdgs.train.workshop.business.HrBusiness;
import th.co.cdgs.train.workshop.entity.Department;
import th.co.cdgs.train.workshop.entity.Employee;
import th.co.cdgs.train.workshop.entity.JobTitle;
import th.co.cdgs.train.workshop.persistance.HrPersistence;
import th.co.cdgs.train.workshop.query.HrQuery;

@Path("employee")
public class EmployeeController {
	
	@EJB
	private HrQuery hrQuery;
	
	@EJB
	private HrBusiness hrBusiness;
	
	@GET
	@Path("/queryEmployeeByCondition/{departmentCode}")
	@Produces(MediaType.APPLICATION_JSON )
	public Response queryEmployeeByCondition (
			@QueryParam("departmentCode")String departmentCode,
			@QueryParam("jobTitleCode")String jobTitleCode,
			@QueryParam("jobType")String jobType,
			@QueryParam("firstName")String firstName,
			@QueryParam("lastName")String lastName,
			@QueryParam("gender")String gender
			) {
		Employee employee = new Employee();
		Department department = new Department();
		department.setDepartmentCode(departmentCode);
		employee.setDepartment(department);
		JobTitle jobtitle = new JobTitle();
		jobtitle.setJobTitleCode(jobTitleCode);
		employee.setJobTitle(jobtitle);
		
		return Response.ok().entity(hrQuery.queryEmployeeByCondition(employee)).build();
	}
	
	@GET
	@Path("/queryEmployeeAndSkillById/{employeeId}")
	@Produces(MediaType.APPLICATION_JSON )
	public Response queryEmployeeAndSkillById (@PathParam("employeeId") String employeeId) {
	    return Response.ok().entity(hrQuery.queryEmployeeAndSkillById(employeeId)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertEmployee(Employee employee) {
		return Response.status(Status.CREATED).entity(hrBusiness.insertEmployee(employee)).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployee(Employee employee) {
		return Response.status(Status.OK).entity(hrBusiness.updateEmployee(employee)).build();
	}

	@DELETE
	@Path("{employeeId}")
	public Response deleteEmployee(@PathParam("employeeId") String employeeId) {
		hrBusiness.deleteEmployee(employeeId);
		return Response.status(Status.OK).build();
	}
	
	
}

