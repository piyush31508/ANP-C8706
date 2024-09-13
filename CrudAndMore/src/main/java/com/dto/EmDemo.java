package com.dto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmDemo {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("students");
		
		Student s1 = new Student();
		s1.setId(104);
		s1.setName("Atharve");
		s1.setAddress("Pune");
		
		
		Certificate cer = new Certificate();
		cer.setCourse("Android");
		cer.setDuration("2 Months");
		
		s1.setCerti(cer);
		
		Student s2 = new Student();
		s2.setId(103);
		s2.setName("Srajal");
		s2.setAddress("Bhopal");
		
		
		Certificate cer2 = new Certificate();
		cer2.setCourse("Web Development");
		cer2.setDuration("3 Months");
		
		s2.setCerti(cer2);
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.merge(s1);
		em.merge(s2);
		em.getTransaction().commit();
		em.close();
		
		
		emf.close();
	}
}
