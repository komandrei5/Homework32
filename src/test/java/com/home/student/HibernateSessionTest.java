package com.home.student;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.home.student.entity.Student;

public class HibernateSessionTest {

    private static SessionFactory sessionFactory = null;

    @BeforeClass
    public static void setUp() throws Exception {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        sessionFactory.close();
    }

    @Test
    public void testSaveOperation() {
        System.out.println("testSaveOperation begins ...");

        Student student1 = new Student("James", "james@example.com");
        Student student2 = new Student("Forest", "forest@example.com");

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(student1);
        session.save(student2);

        session.getTransaction().commit();
        session.close();

        System.out.println("testSaveOperation ends ...");
    }

    @Test
   public void testRetriveOnePerson()
   {
      System.out.println( "testRetriveOnePerson begins .......This is \"R\" of CRUD" );
      testSaveOperation();

      Session session = sessionFactory.openSession();
      session.beginTransaction();

      Student student = (Student) session.get( Student.class, 1 );

      session.getTransaction().commit();
      System.out.println( "Retrieved person from DB is " + student );
      session.close();

       assertEquals("James", student.getName());

       System.out.println("Retrieved student: " + student);
       System.out.println("testRetrieveOneStudent ends ...");
   }

    @Test
    public void testUpdateOneStudent() {
        System.out.println("testUpdateOneStudent begins ...");
        testSaveOperation();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student student = (Student) session.get(Student.class, 1);
        student.setName("UpdatedName");

        session.update(student);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        Student updatedStudent = (Student) session.get(Student.class, 1);

        session.getTransaction().commit();
        session.close();

        assertEquals("UpdatedName", updatedStudent.getName());

        System.out.println("Updated student: " + updatedStudent);
        System.out.println("testUpdateOneStudent ends ...");
    }


    @Test
    public void testDeleteStudent() {
        System.out.println("testDeleteStudent begins ...");
        testSaveOperation();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student student = (Student) session.get(Student.class, 1);

        session.delete(student);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        Student deletedStudent = (Student) session.get(Student.class, 1);

        session.getTransaction().commit();
        session.close();

        assertNull(deletedStudent);

        System.out.println("Deleted student with ID 1");
        System.out.println("testDeleteStudent ends ...");
    }
}