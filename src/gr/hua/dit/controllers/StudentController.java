package gr.hua.dit.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.dit.dao.ApplicationDAO;
import gr.hua.dit.dao.DepartmentDAO;
import gr.hua.dit.dao.EmployeeDAO;
import gr.hua.dit.dao.StudentDAO;
import gr.hua.dit.dao.UserDAO;
import gr.hua.dit.entities.Application;
import gr.hua.dit.entities.Department;
import gr.hua.dit.entities.Employee;
import gr.hua.dit.entities.Student;
import gr.hua.dit.entities.User;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private UserDAO userDao;
	@Autowired
	private StudentDAO studentDAO;
	@Autowired
	private EmployeeDAO empDao;
	@Autowired
	private ApplicationDAO applicationDAO;
	@Autowired
	private DepartmentDAO departmentDAO;
	
	/**Functions for listing/activating unactivate Students*/
	@GetMapping("/lactivate")
	@Transactional
	public String getStudentsForActivation(Model model, Principal principal) {
		
		//Get loggedIn employee, so we can get the Students of Departments that the employee manages
		User user = userDao.findByUserName(principal.getName());
		Employee em = empDao.getEmployee(user.getId());
		
		//List of all unactivate students of employee's departments
		List<Student> unactivateStudents = new ArrayList<>();
		for ( Department d : em.getDepartments()) {
			unactivateStudents.addAll(studentDAO.getUnactiveStudentsOfDepartement(d.getId()));
		}

		model.addAttribute("students", unactivateStudents);
		
		return "finaletoEmpActivate";
	}
	
	@Transactional
	@RequestMapping(value = "/activateStudent", method = RequestMethod.GET)
	public String activateStudent( @RequestParam("stud_id") int stud_id) {
	
		Student s = studentDAO.getStudent(stud_id);
		s.setActivated(true); 
		studentDAO.saveStudent(s); 

		return "redirect:/student/lactivate";
	}
	
	
	/**Functions for listing/approving Student's ApplicationForm */
	@GetMapping("/lapprove")
	@Transactional
	public String approveApplication( Model model) {
		List<Application> unapprovedApps = applicationDAO.getUnapprovedApplications();
		
		for(Application a: unapprovedApps) {
			a.getCreatedBy();
			a.getCreationDate();
			a.getApplicationForm();
		}

		model.addAttribute("applications", unapprovedApps);
		return "finaletoEmpAppr";
	}
	
	@Transactional
	@RequestMapping(value = "/approveStudentApplication", method = RequestMethod.GET)
	public String approveStudentApplication( @RequestParam("app_id") int app_id) {
		
		Application a = applicationDAO.getApplication(app_id);
		applicationDAO.approveApplication(a);

		return "redirect:/student/lapprove";
	}
}
