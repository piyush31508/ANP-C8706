package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.AuthorDao;
import model.Author;
import model.Book;
import util.HibernateUtil;

public class AuthorImp implements AuthorDao {

	EntityManager em;

	@Override
	public void save(Author a) {
		// TODO Auto-generated method stub
		em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
			System.out.println("Author Details inserted sucessfully");
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public Author get(long id) {
		// TODO Auto-generated method stub
		Author a = null;
		em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		try {
			a = em.find(Author.class, id);
		} finally {
			em.close();
		}
		return a;
	}

	@Override
	public void update(Author author) {
		// TODO Auto-generated method stub
		em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(author);
			em.getTransaction().commit();

			System.out.println("Author updated successfully.");
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Book> authorWorks(String name) {
		List<Book> list = new ArrayList<>();
		em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		try {
			Query q = em.createQuery("FROM Book b WHERE b.author.name = :authorName", Book.class);
			q.setParameter("authorName", name);
			list = q.getResultList();
		} finally {
			em.close();
		}

		return list;
	}

}
