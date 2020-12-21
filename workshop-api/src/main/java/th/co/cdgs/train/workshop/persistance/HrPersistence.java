package th.co.cdgs.train.workshop.persistance;

 

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import th.co.cdgs.train.workshop.entity.Employee;
import th.co.cdgs.train.workshop.entity.Skill;
import th.co.cdgs.train.workshop.query.HrQuery;

 

 

@Stateless
public class HrPersistence {
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private HrQuery hrQuery;
    
    public Employee insertEmployee(Employee employee) {
        if(employee != null) {
            String employeeId = hrQuery.queryNextEmployeeId();
            employee.setEmployeeId(employeeId);
            em.persist(employee);    
        }
        return employee;
    }
    
    public Employee updateEmployee(Employee employee) {
        if(employee != null) {
            em.merge(employee);
        }
        return employee;
    }
    
    public void deleteEmployee(String employeeId) {
    	Employee employee = em.find(Employee.class, employeeId);
		em.remove(employee);
    }
    
    public Skill insertSkill(Skill skill) {
        if(skill != null) {
            String skillId = hrQuery.queryNextSkillId();
            skill.setSkillId(skillId);
            em.persist(skill);    
        }
        return skill;
    }
    
    public Skill updateSkill(Skill skill) {
        if(skill != null) {
            em.merge(skill);
        }
        return skill;
    }
    
    public void deleteSkill(String skillId) {
        Skill skill = em.find(Skill.class, skillId);
        em.remove(skill);
    }
    
    
}
 