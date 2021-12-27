package by.itacademy.javaenterprise.lepnikau;


import by.itacademy.javaenterprise.lepnikau.config.SpringPersistenceConfig;
import by.itacademy.javaenterprise.lepnikau.dao.impl.SchoolClassDAOImpl;
import by.itacademy.javaenterprise.lepnikau.dao.impl.StudentDAOImpl;
import by.itacademy.javaenterprise.lepnikau.dao.impl.SubjectDAOImpl;
import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManagerFactory;

public class InsertEntity {

    private static final Logger LOG = LoggerFactory.getLogger(InsertEntity.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringPersistenceConfig.class);
        EntityManagerFactory managerFactory = context.getBean(EntityManagerFactory.class);
        SubjectDAOImpl subjectDAO = context.getBean(SubjectDAOImpl.class);
        //StudentDAOImpl studentDAO = context.getBean(StudentDAOImpl.class);

        /*subjectDAO.setEntityManager(managerFactory.createEntityManager());

        Subject subject = new Subject();
        subject.setId(11L);
        subject.setName("Some subject");*/

        /*Subject saved = subjectDAO.save(subject);
        if (saved != null) {
            System.out.println(saved.toString());
        } else {
            LOG.info("Subject not found");
        }*/
    }
}
