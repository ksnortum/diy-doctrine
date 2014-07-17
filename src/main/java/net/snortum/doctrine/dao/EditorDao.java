package net.snortum.doctrine.dao;

import java.util.List;

import net.snortum.doctrine.model.Editor;
import net.snortum.doctrine.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Data Access Object for {@link Editor}. Inherits basic CRUD.
 * 
 * @author Knute Snortum
 * @version 0.2
 *
 */
@Component
public class EditorDao extends GenericHibernateDao<Editor, Integer> {
	
	/** Tell GenericHibernateDao that the class is Editor */
	public EditorDao() {
		super( Editor.class );
	}

	/** @return a list of all Editors */
	public List<Editor> list() {
		Session session = HibernateUtil.sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings( "unchecked" )
		List<Editor> editors = session.createCriteria( Editor.class ).list();
		session.getTransaction().commit();

		return editors;
	}
	
	/**
	 * Get editor ID by username
	 * 
	 * @param username the username to find
	 * @return ID of the user, or -1 if nut found
	 */
	public int idByUsername( String username ) {
		Session session = HibernateUtil.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria( Editor.class );
		Editor found = (Editor) criteria
				.add( Restrictions.eq( "username", username) ).uniqueResult();
		session.getTransaction().commit();
		
		return found == null ? -1 : found.getId(); 
	}
}
