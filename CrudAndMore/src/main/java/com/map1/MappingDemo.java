package com.map1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MappingDemo {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LearningUnit");
		EntityManager em = emf.createEntityManager();
		
		Employee e1 = new Employee(), e2 = new Employee();
		
		e1.setEid(12);
		e1.setName("Suresh");
		
		e2.setEid(13);
		e2.setName("Ramesh");
		
		Project p1= new Project(), p2 = new Project();
		
		p1.setPid(110);
		p1.setProjectName("ChatBot");
		p2.setPid(111);
		p2.setProjectName("LMS");
		
		List<Employee> l1 = new ArrayList<>();
		List<Project> l2 = new ArrayList<>();
		
		l1.add(e1);
		l1.add(e2);
		
		l2.add(p1);
		l2.add(p2);
		
		e1.setProjects(l2);
		l2.remove(1);
		e2.setProjects(l2);
		p2.setEmps(l1);
		
		em.getTransaction().begin();
		
//		em.persist(e1);
//		em.persist(e2);
//		em.persist(p1);
//		em.persist(p2);
		em.merge(e2);
		
		
		em.getTransaction().commit();
		em.close();

	}
}
