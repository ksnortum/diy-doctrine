package net.snortum.doctrine.dao;

import java.io.Serializable;

import net.snortum.doctrine.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * A generic implementation of Hibernate CRUD behavior.
 * 
 * @author Knute Snortum
 * @version 0.1
 *
 * @param <T>
 *            The type of the object
 * @param <PK>
 *            The type of the primary key (ID)
 */
public class GenericHibernateDao<T, PK extends Serializable>
		implements GenericDao<T, PK> {
	
	private static final Logger LOG = 
			Logger.getLogger( GenericHibernateDao.class );

	private Class<T> type;

	/**
	 * Create an instance of this DAO
	 * 
	 * @param type
	 *            The type of the object
	 */
	public GenericHibernateDao( Class<T> type ) {
		this.type = type;
	}

	@SuppressWarnings( "unchecked" ) // session.save() returns PK
	@Override
	public PK create( T newInstance ) {
		Session session = HibernateUtil.sessionFactory.getCurrentSession();
		session.beginTransaction();
		PK id = null;
		
		try {
			id = (PK) session.save( newInstance );
			session.getTransaction().commit();
		} catch ( HibernateException e ) {
			LOG.error( e.toString() );
			session.getTransaction().rollback();
			id = null;
		}
		
		return id;
	}

	@Override
	public T read( PK id ) {
		Session session = HibernateUtil.sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings( "unchecked" )
		T obj = (T) session.get( type, id );
		session.getTransaction().commit();

		return obj;
	}

	@Override
	public void update( T transientObject ) {
		Session session = HibernateUtil.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update( transientObject );
		session.getTransaction().commit();
	}

	@Override
	public void delete( T persistentObject ) {
		Session session = HibernateUtil.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete( persistentObject );
		session.getTransaction().commit();
	}

}
