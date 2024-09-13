package com.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.map.Answer;

public class Criteria {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LearningUnit");
		EntityManager em = emf.createEntityManager();
		
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Answer> cq = cb.createQuery(Answer.class);
        
        Root<Answer> answerRoot = cq.from(Answer.class);
        
        cq.select(answerRoot).where(cb.equal(answerRoot.get("aId"),111));
        
        TypedQuery<Answer> query = em.createQuery(cq);
        List<Answer> customers = query.getResultList();
        
        // Process the results
        customers.forEach(Answer -> System.out.println(Answer.getAnswer()));


	}
}
