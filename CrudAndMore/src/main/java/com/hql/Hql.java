package com.hql;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.map.Answer;

public class Hql {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LearningUnit");
		EntityManager em = emf.createEntityManager();
		
		String query = "from Answer where answer like :x";
		
		em.getTransaction().begin();
		
		Query q = em.createQuery(query);
		q.setParameter("x", "%OOP");
		
		List<Answer> list = q.getResultList();
		
		for(Answer s:list) {
			System.out.println(s.getAnswer());
		}
		
//		Query q2 = em.createQuery("Delete from Answer where aId=: n");
//		q2.setParameter("n", 112);
//		
//		int r = q2.executeUpdate();
//		System.out.println("delted: "+r);
		
//		Query q2 = em.createQuery("update Answer set aId=:n1 where aId=:n2");
//		q2.setParameter("n1", 1110);
//		q2.setParameter("n2", 110);
//		int r = q2.executeUpdate();
//		System.out.println(r+" updated");
		
		Query w = em.createQuery("select q.question, q.qId, a.answer from Question q INNER JOIN q.answer as a");
		List<Object []> list2 = w.getResultList();
		
		for(Object[] arr: list2) {
			System.out.println(Arrays.toString(arr));
		}
		em.getTransaction().commit();
		em.close();
		
		
		
		emf.close();
		
	}
}
