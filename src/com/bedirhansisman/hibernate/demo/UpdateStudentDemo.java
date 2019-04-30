package com.bedirhansisman.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bedirhansisman.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			//UPDATING ONE STUDENT IS LIKE THAT
			
			int studentId = 13;
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("\nUpdating Student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			myStudent.setEmail("daffy.ducky@gmail.com");
						
			// commit the transaction
			session.getTransaction().commit();
			
			//UPDATING ALL STUDENT TABLE IS LIKE THAT
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("Updating email for all students");
			
			//example
			session.createQuery("update Student set lastName='Sisman'").executeUpdate();

			//different example
			session.createQuery("update Student s set lastName='Diger' where s.firstName='Elif' OR s.firstName='Sezgin' OR s.firstName='Naci' OR s.firstName='Daffy'").executeUpdate();

			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			factory.close();
		}
		
	}

}









