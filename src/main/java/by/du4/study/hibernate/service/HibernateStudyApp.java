package by.du4.study.hibernate.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;


public class HibernateStudyApp {

    private static final Logger LOG = LoggerFactory.getLogger(HibernateStudyApp.class);

    public static void main(String[] args) {

        LOG.trace("App is started.");

        Set<? extends Number> someSet = new HashSet<>();

        someSet.add(new Integer(12));
        someSet.add(new Double(12.0));


    }
}
