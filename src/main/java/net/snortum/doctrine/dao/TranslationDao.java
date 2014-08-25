package net.snortum.doctrine.dao;

import java.util.List;

import net.snortum.doctrine.model.Translation;
import net.snortum.doctrine.util.HibernateUtil;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * Data Access Object for {@link Translation}. Inherits basic CRUD.
 * 
 * @author Knute Snortum
 * @version 0.1
 *
 */
@Component
public class TranslationDao extends GenericHibernateDao<Translation, Integer> {
	
	/** Tell GenericHibernateDao that the class is Translation */
	public TranslationDao() {
		super( Translation.class );
	}

	/** @return a list of all Translations */
	public List<Translation> list() {
		Session session = HibernateUtil.sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings( "unchecked" )
		List<Translation> translations = 
				session.createCriteria( Translation.class ).list();
		session.getTransaction().commit();

		return translations;
	}

}
