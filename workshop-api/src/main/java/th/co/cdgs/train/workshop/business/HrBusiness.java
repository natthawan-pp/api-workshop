package th.co.cdgs.train.workshop.business;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.co.cdgs.train.workshop.entity.Employee;
import th.co.cdgs.train.workshop.entity.Skill;
import th.co.cdgs.train.workshop.persistance.HrPersistence;
import th.co.cdgs.train.workshop.query.HrQuery;

@Stateless
@LocalBean
public class HrBusiness {
	
	
    @EJB
    private HrPersistence hrPersistance;
	
	public Employee insertEmployee(Employee employee) {
		if(employee != null) {
			hrPersistance.insertEmployee(employee);
		}
		return employee;
	}

	public Employee updateEmployee(Employee employee) {
		if(employee != null) {
			hrPersistance.updateEmployee(employee);
		}
		return employee;
	}

    public void deleteEmployee(String employeeId) {
		if(employeeId != null) {
			hrPersistance.deleteEmployee(employeeId);
		}
    }
    
    public Skill insertSkill(Skill skill) {
        if(skill != null) {
            hrPersistance.insertSkill(skill);
        }
        return skill;
    }
    
    public Skill updateSkill(Skill skill) {
        if(skill != null) {
        	hrPersistance.updateSkill(skill);
        }
        return skill;
    }
    
    public void deleteSkill(String skillId) {
    	if(skillId != null) {
			hrPersistance.deleteSkill(skillId);
		}
    }

}
