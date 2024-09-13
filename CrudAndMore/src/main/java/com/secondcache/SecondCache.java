package com.secondcache;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.map.Answer;

public class SecondCache {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LearningUnit");
		EntityManager em1 = emf.createEntityManager();
		EntityManager em2 = emf.createEntityManager();

		em1.getTransaction().begin();
		
		Answer a1 = em1.find(Answer.class, 111);
		System.out.println(a1);
		em1.getTransaction().commit();
		em1.close();
		
		
		em2.getTransaction().begin();

		Answer a2 = em2.find(Answer.class, 111);
		System.out.println(a2);

		em2.getTransaction().commit();
		em2.close();

	}
}
