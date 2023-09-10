package com.home.student;
import org.hibernate.Session;
import com.home.student.entity.Student;

public class RunExample {
    public static void main(String[] args) {
        Session session = HibernateSession.getSessionFactory().openSession();

        Student student = new Student();
        student.setName("Andrew");
        student.setEmail("test@email.com");

        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();

        Student retrievedStudent = (Student) session.get(Student.class, student.getId());
        System.out.println("Retrieved Student: " + retrievedStudent);

        HibernateSession.shutdown();
    }
}