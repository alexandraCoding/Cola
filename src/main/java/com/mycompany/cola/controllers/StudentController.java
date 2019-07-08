package com.mycompany.cola.controllers;

import com.mycompany.cola.entities.Student;
import com.mycompany.cola.services.StudentService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/")
public class StudentController {

	 @RequestMapping(method = RequestMethod.GET)
        public String indexPage(ModelMap model){
            return "index";
        }

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value= "students", method = RequestMethod.GET)
	public String getAllStudents(ModelMap model) {
            StudentService ss = new StudentService();
            List<Student> students = ss.getAllStudents();
            model.addAttribute("students", students);
            return "students";
    }

	 @RequestMapping(value = "/addstudent", method = RequestMethod.GET)
	public String add(ModelMap model) {
            model.addAttribute("title", "Add");
            model.addAttribute("what", "save");
            model.addAttribute("student", new Student());
            return "addedit";
        }
        
        @RequestMapping(value = "/studentsave", method = RequestMethod.POST)
	public String addsave(ModelMap model,@ModelAttribute("student") Student s) {
            StudentService ss = new StudentService();
            if (s.getId()==0){//new student
                ss.saveStudent(s.getFirstName(),s.getLastName(),s.getSection());
                model.addAttribute("message", "Insert Completed");
            }else{//update old student
                ss.updateStudent(s);
                model.addAttribute("message", "Update Completed");
            }
            List<Student> students = ss.getAllStudents();
            model.addAttribute("students", students);
            return "students";
        }

}
