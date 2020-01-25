package com.arava;

import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by Nidhal Dogga
 * Date : 25/01/2020 23:56
 * All rights reserved.
 */

@Component
public class Seeder implements CommandLineRunner {

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  @Override
  public void run(String... args) throws Exception {
    Session session = entityManagerFactory.unwrap(SessionFactory.class)
            .openSession();
    session.beginTransaction();
    InputStream is = new ClassPathResource("sql/seed_referential_tables.sql")
            .getInputStream();
    String sql = IOUtils.toString(is, Charset.defaultCharset());
    session.createSQLQuery(sql).executeUpdate();
    session.getTransaction().commit();
    session.close();
  }

}
