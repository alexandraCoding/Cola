/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cola.services;

import com.mycompany.cola.dao.StudentDAO;
import com.mycompany.cola.entities.Student;
import java.util.List;

/**
 *
 * @author alexa
 */
public class StudentService {
    
    
    public List<Student> getAllStudents(){
    StudentDAO sdao=new StudentDAO();
    return sdao.getAllStudents();
    
    
    }
     public int saveStudent(String firstName, String lastName, String section){
        StudentDAO sdao = new StudentDAO();
        return sdao.saveStudent(firstName,lastName,section);
    }
    
    public void updateStudent(Student s){
        StudentDAO sdao = new StudentDAO();
        sdao.updateStudent(s.getId(), s.getSection());
    }
    
}
