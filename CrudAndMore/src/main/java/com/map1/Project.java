package com.map1;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Project {
	@Id
	
	private int pid;
	@Column(name="Project_Name")
	private String projectName;
	
	@ManyToMany(mappedBy = "projects")
	private List<Employee> emps;

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(int pid, String projectName, List<Employee> emps) {
		super();
		this.pid = pid;
		this.projectName = projectName;
		this.emps = emps;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Employee> getEmps() {
		return emps;
	}

	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}
}
