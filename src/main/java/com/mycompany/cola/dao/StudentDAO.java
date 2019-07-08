/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cola.dao;

import com.mycompany.cola.entities.Student;
import com.mycompany.cola.hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author alexa
 */
public class StudentDAO {
    
	/**
	 * This method saves a Student object in database
	 */
	public int saveStudent(String firstName, String lastName, String section) {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setSection(section);

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		int id = (Integer) session.save(student);
		session.getTransaction().commit();
		session.close();
		return id;
	}

	/**
	 * This method returns list of all persisted Student objects/tuples from
	 * database
	 */
	public List<Student> getAllStudents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Student> students = (List<Student>) session.createQuery(
				"FROM Student s ORDER BY s.firstName ASC").list();

		session.getTransaction().commit();
		session.close();
		return students;
	}

	/**
	 * This method updates a specific Student object
	 */
	public void updateStudent(int id, String section) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
// by default to save epistrefei to ID tou class/ entity 
// de peirazoume me set to id /// sto psari to kefali de to peirazoume!!!
		Student student = (Student) session.get(Student.class, id);
		student.setSection(section);
		//session.update(student);//No need to update manually as it will be updated automatically on transaction close.
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * This method deletes a specific Student object
	 */
	public void deleteStudent(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Student student = (Student) session.get(Student.class, id);
		session.delete(student);
		session.getTransaction().commit();
		session.close();
	}
}
