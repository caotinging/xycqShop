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

	public static Session getSession() {
		Session session = factory.openSession();
		return session;
	}

	public static Session getCurrentSession() {
		Session session = factory.getCurrentSession();
		return session;
	}

	public static void closeFactory() {
		factory.close();
	}
	
	public static Transaction startTransaction() {
		return factory.getCurrentSession().beginTransaction();
	}
}
