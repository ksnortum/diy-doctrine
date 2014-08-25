package net.snortum.doctrine.dao;

import java.util.List;

import net.snortum.doctrine.model.Idea;
import net.snortum.doctrine.util.HibernateUtil;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * Data Access Object for {@link Idea}. Inherits basic CRUD.
 * 
 * @author Knute Snortum
 * @version 0.1
 *
 */
@Component
public class IdeaDao extends GenericHibernateDao<Idea, Integer> {
	
	/** Tell GenericHibernateDao that the class is Idea */
	public IdeaDao() {
		super( Idea.class );
	}

	/** @return a list of all Books */
	public List<Idea> list() {
		Session session = HibernateUtil.sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings( "unchecked" )
		List<Idea> translations = 
				session.createCriteria( Idea.class ).list();
		session.getTransaction().commit();

		return translations;
	}

}
