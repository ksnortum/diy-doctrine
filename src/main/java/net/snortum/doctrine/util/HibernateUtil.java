package net.snortum.doctrine.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Get a static SessionInfo Factory from the Hibernate configuration
 * 
 * @author Knute
 * @version 0.3
 */
public class HibernateUtil {
	
	static {
		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings( config.getProperties() )
				.buildServiceRegistry();
		sessionFactory = config.buildSessionFactory( serviceRegistry );
	}
	
	public static SessionFactory sessionFactory;

}
