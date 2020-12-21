
package th.co.cdgs.train.workshop.query;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdgs.train.workshop.entity.Department;
import th.co.cdgs.train.workshop.entity.Employee;
import th.co.cdgs.train.workshop.entity.JobTitle;
import th.co.cdgs.train.workshop.entity.Skill;

@Stateless
@LocalBean
public class HrQuery {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Department> queryDepartment(){
		String sql = "select department_code, department_name from department order by department_code";
		Query query = em.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		List<Department> results = new ArrayList<>();
		for (Object[] obj : list) {
			Department department = new Department();
			department.setDepartmentCode((String)obj[0]);
			department.setDepartmentName((String)obj[1]);
			results.add(department);
		}
		return results;
	}
	
	public List<JobTitle> queryJobTitle(){
		String sql = "select job_title_code, job_title_name, job_type from job_title order by job_title_code";
		Query query = em.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		List<JobTitle> results = new ArrayList<>();
		for (Object[] obj : list) {
			JobTitle jobtitle = new JobTitle();
			jobtitle.setJobTitleCode((String)obj[0]);
			jobtitle.setJobTitleName((String)obj[1]);
			jobtitle.setJobType((String)obj[2]);
			results.add(jobtitle);
		}
		return results;
	} 
	
	public List<Employee> queryEmployeeByCondition(Employee employee){
		
        if(employee == null) {
            throw new IllegalArgumentException("The all parameter is blank or null. ");
        }
	
		String sql = "	select e.employee_id, e.title, e.first_name, e.last_name, e.gender" + 
				"	, e.department_code, e.job_title_code, d.department_name, j.job_title_name, j.job_type" + 
				"	from employee e" + 
				"	join department d on (e.department_code = d.department_code)" + 
				"	join job_title j on (e.job_title_code = j.job_title_code) where 1=1 ";

		if (employee.getDepartment().getDepartmentCode() != null && !"".equals(employee.getDepartment().getDepartmentCode())) {
			sql += " and e.department_code = :departmentCode";
		}
		if (employee.getJobTitle().getJobTitleCode() != null && !"".equals(employee.getJobTitle().getJobTitleCode())) {
			sql += " and e.job_title_code = :jobTitleCode";
		}
		if (employee.getJobTitle().getJobType() != null && !"".equals(employee.getJobTitle().getJobType())) {
			sql += " and j.job_type = :jobType";
		}
		if (employee.getFirstName() != null && !"".equals(employee.getFirstName())) {
			sql += " and e.first_name like :firstName";
		}
		if (employee.getLastName() != null && !"".equals(employee.getLastName())) {
			sql += " and e.last_name like :lastName";
		}
		if (employee.getGender() != null && !"".equals(employee.getGender())) {
			sql += " and e.gender= :gender";
		}
		
		Query query = em.createNativeQuery(sql);
		
		if (employee.getDepartment().getDepartmentCode() != null && !"".equals(employee.getDepartment().getDepartmentCode())) {
			query.setParameter("departmentCode", employee.getDepartment().getDepartmentCode());
		}
		if (employee.getJobTitle().getJobTitleCode() != null && !"".equals(employee.getJobTitle().getJobTitleCode())) {
			query.setParameter("jobTitleCode", employee.getJobTitle().getJobTitleCode() );
		}
		if (employee.getJobTitle().getJobType() != null && !"".equals(employee.getJobTitle().getJobType())) {
			query.setParameter("jobType", employee.getJobTitle().getJobType() );
		}
		if (employee.getFirstName() != null && !"".equals(employee.getFirstName())) {
			query.setParameter("firstName","%"+ employee.getFirstName() +"%");
		}
		if (employee.getLastName() != null && !"".equals(employee.getLastName())) {
			query.setParameter("lastName","%"+ employee.getLastName() +"%");
		}
		if (employee.getGender() != null && !"".equals(employee.getGender())) {
			query.setParameter("gender","%"+ employee.getGender() +"%");
		}
		
		List<Object[]> list = query.getResultList();
		List<Employee> results = new ArrayList<>();
		for (Object[] obj : list) {
			Employee emp = new Employee();
			emp.setEmployeeId((String)obj[0]);
			emp.setTitle((String)obj[1]);
			emp.setFirstName((String)obj[2]);
			emp.setLastName((String)obj[3]);
			emp.setGender((String)obj[4]);
			Department dep = new Department();
			dep.setDepartmentCode((String)obj[5]);
			dep.setDepartmentName((String)obj[7]);
			emp.setDepartment(dep);
			JobTitle jobtitle = new JobTitle();
			jobtitle.setJobTitleCode((String)obj[6]);
			jobtitle.setJobTitleName((String)obj[8]);
			jobtitle.setJobType((String)obj[9]);
			emp.setJobTitle(jobtitle);
			results.add(emp);
		}
		return results;
	}
	
	public Employee queryEmployeeById(String employeeId) {
        if(employeeId == null) {
            throw new IllegalArgumentException("The 'employeeId' is blank or null. ");
        }
        String sql = "select e.employee_id, e.title, e.first_name, e.last_name, e.gender, e.department_code, e.job_title_code, e.address" +
                ", d.department_name, j.job_title_name " +
                "from employee e " +
                "join department d on (e.department_code = d.department_code) " +
                "join job_title j on (e.job_title_code = j.job_title_code) " +
                "where e.employee_id = :employeeId " +
                "order by employee_id ";
        Query query = em.createNativeQuery(sql);
        query.setParameter("employeeId", employeeId);
        
        Object[] obj = (Object[])query.getSingleResult();
        Employee employee = new Employee();
        employee.setEmployeeId((String)obj[0]);
        employee.setTitle((String)obj[1]);
        employee.setFirstName((String)obj[2]);
        employee.setLastName((String)obj[3]);
        employee.setGender((String)obj[4]);
        Department department = new Department();
        department.setDepartmentCode((String)obj[5]);
        department.setDepartmentName((String)obj[8]);
        employee.setDepartment(department);
        JobTitle jobTitle = new JobTitle();
        jobTitle.setJobTitleCode((String)obj[6]);
        jobTitle.setJobTitleName((String)obj[9]);
        employee.setJobTitle(jobTitle);
        employee.setAddress((String)obj[7]);
       
        return employee;
	}
	
	public List<Skill> querySkillByEmployeeId(String employeeId) {
        if(employeeId == null) {
            throw new IllegalArgumentException("The 'employeeId' is blank or null. ");
        }
        String sql = "select skill_id, employee_id, skill_name, skill_desc " + 
        		"from skill " + 
        		"where employee_id = :employeeId " + 
        		"order by skill_id ";
        Query query = em.createNativeQuery(sql);
        query.setParameter("employeeId", employeeId);
        
        List<Object[]> list = query.getResultList();
		List<Skill> results = new ArrayList<>();
		for (Object[] obj : list) {
			Skill skill = new Skill();
			skill.setSkillId((String)obj[0]);
			Employee emp = new Employee();
			emp.setEmployeeId((String)obj[1]);
			skill.setEmployee(emp);
			skill.setSkillId((String)obj[2]);
			skill.setSkillName((String)obj[3]);
			results.add(skill);
		}
		return results;
	}
	
	public Employee queryEmployeeAndSkillById(String employeeId) {
		Employee emp = queryEmployeeById(employeeId);
		if (employeeId != null) {
			emp.setSkill(querySkillByEmployeeId(employeeId));
		}
	    return emp;
	}
	
	public String queryNextEmployeeId() {
		String sql = "select coalesce(max(employee_id) + 1 , 1) from employee";
        Query query = em.createNativeQuery(sql);
        double maxEmployee = (double)query.getSingleResult();
		return String.format("%05d", (int)maxEmployee);
	}
	
	public String queryNextSkillId() {
		String sql = "select coalesce(max(skill_id) + 1 , 1) from skill";
        Query query = em.createNativeQuery(sql);
        double maxSkill = (double)query.getSingleResult();
		return String.format("%05d", (int)maxSkill);
	}
	
	
	
	
}