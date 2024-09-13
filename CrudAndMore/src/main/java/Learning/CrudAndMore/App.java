package Learning.CrudAndMore;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.dto.Address;
//import com.dto.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("students");
    	EntityManager em = emf.createEntityManager();
    	
//    	Student s1 = new Student(102,"Jatin","JBP");
//    	
//    	em.getTransaction().begin();
//    	em.persist(s1);
//    	em.getTransaction().commit();
//    	em.close();
    	
    	Address ad = new Address();
    	ad.setStreet("Street 1");
    	ad.setCity("Delhi");
    	ad.setAddedDate(new Date());
    	ad.setX(50.5);
    	ad.setImage(null);
    	
    	FileInputStream pic = new FileInputStream("src/main/java/about-me.jpg");
    	byte []data = new byte[pic.available()];
    	pic.read(data);
    	ad.setImage(data);
    	
    	em.getTransaction().begin();
    	em.persist(ad);
    	em.getTransaction().commit();
    	System.out.println("Done........");
    	em.close();
    }
}
