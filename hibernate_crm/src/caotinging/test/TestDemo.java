package caotinging.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestDemo
{
  @Test
  public void test1()
  {
    Configuration conf = new Configuration().configure();
    SessionFactory factory = conf.buildSessionFactory();
    Session session = factory.openSession();
    System.out.println(session);
    session.close();
    factory.close();
  }
}