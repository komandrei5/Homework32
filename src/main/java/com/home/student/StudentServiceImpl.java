package com.home.student;

import com.home.student.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final SessionFactory sessionFactory;

    public StudentServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Student> getAll() {
        SessionFactory sessionFactory1 = HibernateSession.getSessionFactory();
        Session session = sessionFactory1.openSession();
        return  session.createQuery("SELECT a FROM Student a", Student.class).getResultList();
    }
    @Override
    public Student get(Long id) {
//        try (Session session = sessionFactory.openSession()) {
         //   return session.get(Student.class, id);
        return null;
    }

    @Override
    public Student update(Student student) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            Student updatedStudent = (Student) session.merge(student);
//            session.getTransaction().commit();
//            return updatedStudent;
//        }
        ;
    }

    @Override
    public Long create(Student student) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Long id = (Long) session.save(student);
            session.getTransaction().commit();
            return id;
        }
    }

    @Override
    public void delete(Student student) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        }
    }
}