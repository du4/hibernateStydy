package by.du4.study.hibernate;

import by.du4.study.hibernate.beans.Post;
import by.du4.study.hibernate.beans.PostDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MappingTest {

    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;
    private static Session session;
    private static final Logger LOG = LoggerFactory.getLogger(MappingTest.class);

    private static final String sql = "select version()";

    @Before
    public void init(){
        try {
            // Creating StandardServiceRegistryBuilder
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

            // Hibernate settings which is equivalent to hibernate.cfg.xml's properties
            Map<String, String> dbSettings = new HashMap<>();
            dbSettings.put(Environment.URL, "jdbc:postgresql://127.0.0.1:5432/hibernate_study");
            dbSettings.put(Environment.USER, "qcross");
            dbSettings.put(Environment.PASS, "measuring");
            dbSettings.put(Environment.DRIVER, "org.postgresql.Driver");
            dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL82Dialect");
            dbSettings.put(Environment.SHOW_SQL, "true");
            dbSettings.put(Environment.FORMAT_SQL, "true");
            dbSettings.put(Environment.HBM2DDL_AUTO, "create");
            dbSettings.put(Environment.USE_SQL_COMMENTS, "true");

            // Apply database settings
            registryBuilder.applySettings(dbSettings);
            // Creating registry
            standardServiceRegistry = registryBuilder.build();
            // Creating MetadataSources
            MetadataSources sources = new MetadataSources(standardServiceRegistry);
            sources.addAnnotatedClass(Post.class);
            sources.addAnnotatedClass(PostDetails.class);
            // Creating Metadata
            Metadata metadata = sources.getMetadataBuilder().build();
            // Creating SessionFactory
            sessionFactory = metadata.getSessionFactoryBuilder().build();

            session = sessionFactory.openSession();

        }
        catch (Exception ex) {
            LOG.error("Initial SessionFactory creation failed." ,ex);
        }
    }


    @Test()
    public void aSaveTest() {
        PostDetails postDetails = new PostDetails("du5");
        Post post = new Post("title", postDetails);
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(post);
        session.flush();
        transaction.commit();
    }


    @Test
    public void bGetTest() {
        Post postFromDB = session.get(Post.class, 1l);

        LOG.info(postFromDB.toString());
    }


    @After
    public void finalizeTest(){
        if (session.isOpen()) session.close();
        sessionFactory.close();
    }
}
