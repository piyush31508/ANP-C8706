package com.map;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MapDemo {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LearningUnit");
		EntityManager em = emf.createEntityManager();

		Question q1 = new Question();
		q1.setqId(10);
		q1.setQuestion("What is Java?");
		Answer a1 = new Answer();
		a1.setaId(110);
		a1.setAnswer("Java is a programming language");
		a1.setQ(q1);
		Answer a2 = new Answer();
		a2.setaId(111);
		a2.setAnswer("Java is based on OOP");
		a2.setQ(q1);

		Answer a3 = new Answer();
		a3.setaId(112);
		a3.setAnswer("Java was developed by James Gosling");
		a3.setQ(q1);

		List<Answer> aList = new ArrayList<Answer>();
		
		aList.add(a1);
		aList.add(a2);
		aList.add(a3);
		
//		q1.setAnswer(aList);
		
		em.getTransaction().begin();
		
//		em.persist(q1);
		
		em.getTransaction().commit();
		
		Question q = (Question) em.find(Question.class, 10);
		System.out.println(q.getqId()+" "+q.getQuestion());
		System.out.println(q.getAnswer().size());

		
//		for(Answer a:q.getAnswer()) {
//			System.out.println(a.getAnswer());
//		}

		
		em.close();
		
	}

}