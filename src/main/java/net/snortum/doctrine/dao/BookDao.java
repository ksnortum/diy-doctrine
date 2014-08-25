package net.snortum.doctrine.dao;

import java.util.List;

import net.snortum.doctrine.model.Book;
import net.snortum.doctrine.util.HibernateUtil;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * Data Access Object for {@link Book}. Inherits basic CRUD.
 * 
 * @author Knute Snortum
 * @version 0.1
 *
 */
@Component
public class BookDao extends GenericHibernateDao<Book, Integer> {
	
	/** Tell GenericHibernateDao that the class is Book */
	public BookDao() {
		super( Book.class );
	}

	/** @return a list of all Books */
	public List<Book> list() {
		Session session = HibernateUtil.sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings( "unchecked" )
		List<Book> translations = 
				session.createCriteria( Book.class ).list();
		session.getTransaction().commit();

		return translations;
	}

}
