package th.co.cdgs.train.workshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Job_title")
public class JobTitle {
	private String jobTitleCode;
	private String jobTitleName;
	private String jobType;
	
	@Id
	@Column(name = "job_title_code", unique = true, nullable = false, length = 3)
	public String getJobTitleCode() {
		return jobTitleCode;
	}
	public void setJobTitleCode(String jobTitleCode) {
		this.jobTitleCode = jobTitleCode;
	}
	
	@Column(name = "job_title_name", length = 50)
	public String getJobTitleName() {
		return jobTitleName;
	}
	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}
	
	@Column(name = "job_type", length = 1)
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
}
