package caotinging.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	private static SessionFactory factory;
	
	static {
		Configuration conf = new Configuration().configure();
		factory = conf.buildSessionFactory();
	}
	
	public static Session getNewSession() {
		return factory.openSession();
	}
	
	public static Session getCurrentSession() {
		return factory.getCurrentSession();
	}
	
	public static void closeFactory() {
		factory.close();
	}
	
	public static Transaction startTransaction() {
		return factory.getCurrentSession().beginTransaction();
	}
}
