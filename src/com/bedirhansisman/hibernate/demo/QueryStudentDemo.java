package com.bedirhansisman.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bedirhansisman.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		
		try {		
			
			// start a transaction
			session.beginTransaction();
			
			// query students : all
			List<Student> students = session.createQuery("from Student").getResultList(); // "Student" is the class name, Not table name. S letter is big!!
			
			// display the students
			displayStudents(students);
			
			//query students : lastName='Sisman'
			students = session.createQuery("from Student s where s.lastName='Sisman'").getResultList(); // "lastName" is the field name, Not column name. it is camelcase!!
			
			// display the students
			System.out.println("\n\nDisplay the students who have last name of Sisman");
			displayStudents(students);
			
			//query students : lastName='Sisman' OR firstName='Daffy'
			students = session.createQuery("from Student s where s.lastName='Sisman' OR s.firstName='Daffy'").getResultList();
			
			// display the students
			System.out.println("\n\nDisplay the students who have last name of Sisman OR first name of Daffy");
			displayStudents(students);
			
			//query students : where email LIKE '%@gmail.com'
			students = session.createQuery("from Student s where s.email LIKE '%@gmail.com'").getResultList();
			
			// display the students
			System.out.println("\n\nDisplay the students who have email ends LIKE '@gmail.com'");
			displayStudents(students);
						
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			factory.close();
			
		}
		
	}

	private static void displayStudents(List<Student> students) {
				
		for(Student tempStudent : students) {
			
			System.out.println(tempStudent.getFirstName() + " " + tempStudent.getLastName());
			
		}
		
	}

}









