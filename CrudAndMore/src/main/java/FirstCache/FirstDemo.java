package FirstCache;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.map.Answer;

public class FirstDemo {
	
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LearningUnit");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Answer a = em.find(Answer.class, 111); 
        System.out.println(a);
        System.out.println("Code");
        Answer a2 = em.find(Answer.class, 111);
        System.out.println(a);
        
        System.out.println(em.contains(a2));
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
