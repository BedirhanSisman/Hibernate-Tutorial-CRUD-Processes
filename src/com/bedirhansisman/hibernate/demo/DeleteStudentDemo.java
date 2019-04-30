package com.bedirhansisman.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bedirhansisman.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			//DELETE ONE STUDENT IS LIKE THAT
			
			int studentId = 7;
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("\nDeleting Student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Deleting student with id: " + studentId);
			session.delete(myStudent);
						
			// commit the transaction
			session.getTransaction().commit();
			
			//DELETING MANY STUDENTS IN TABLE IS LIKE THAT
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("delete from Student where lastName='Diger'").executeUpdate();

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









