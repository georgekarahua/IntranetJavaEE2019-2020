package gr.hua.dit.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.dao.ApplicationDAO;
import gr.hua.dit.dao.DepartmentDAO;
import gr.hua.dit.dao.StudentDAO;
import gr.hua.dit.dao.UserDAO;
import gr.hua.dit.entities.Application;
import gr.hua.dit.entities.Department;
import gr.hua.dit.entities.Form;
import gr.hua.dit.entities.Student;
import gr.hua.dit.entities.User;

@RestController
@RequestMapping("/api")
public class StudentRestController {
		
	@Autowired
	private UserDAO userDao;
	@Autowired
	private StudentDAO studentDAO;
	@Autowired
	private ApplicationDAO applicationDAO;
	@Autowired
	private DepartmentDAO departmentDAO;
	
		// Get Student's Info
		@Transactional
		@GetMapping("/student/{username}")
		public Student getStudentInfo(@PathVariable String username) {
			User u = userDao.findByUserName(username);
			Student s = studentDAO.getStudent(u.getId());
			
			//Build New Student and serve this to Client
			Student responseStud = new Student();
//			responseStud.setRanking(s.getRanking());
			responseStud.setActivated(s.isActivated());
			responseStud.setFirstName(s.getFirstName());
			responseStud.setLastName(s.getLastName());
			responseStud.setId(s.getId());
			responseStud.setPhone(s.getPhone());
			if (s.getApplication()== null) {
				responseStud.setApplication(new Application());
			} else {
				responseStud.setApplication(s.getApplication());
			}
//			responseStud.setApplication(s.getApplication());
			
			return responseStud;
		}
		
		//Update Student's Info
		@Transactional
		@RequestMapping(value = "/student/{username}", method = RequestMethod.PUT, produces = { "application/json" })
		public Student updateStudentInfo(@PathVariable("username") String username, @RequestBody Student studentIn) {
			User u = userDao.findByUserName(username);
			Student s = studentDAO.getStudent(u.getId());
			s.setFirstName(studentIn.getFirstName());
			s.setLastName(studentIn.getLastName());
			s.setPhone(studentIn.getPhone());
			
			studentDAO.saveStudent(s);
			return s;
		}
		
		//Create Student's Application/Form
		@Transactional
        @RequestMapping(value = "/student/application/{username}", method = RequestMethod.PUT, produces = { "application/json" })
		public Application saveApplication(@PathVariable("username") String username, @RequestBody Form form) {
			System.out.println(form.getAnnualIncome());
			User u = userDao.findByUserName(username);
			Student s = studentDAO.getStudent(u.getId());
			
			Application app = new Application();
			app.setApplicationForm(form);
			app.setCreationDate(LocalDateTime.now());
			app.setCreatedBy(s);
			applicationDAO.saveApplication(app);
			s.setApplication(app);
			studentDAO.saveStudent(s);
			return app; 
		}
		
		@Transactional
		@RequestMapping(value = "/student/getRanking/{username}", method = RequestMethod.GET, produces = { "application/json" })
		public Map<String,Integer> getStudentRanking(@PathVariable("username") String username) {
			User u = userDao.findByUserName(username);
			Student s = studentDAO.getStudent(u.getId());
			//Find Department of User
			Department d = departmentDAO.getDepartment(s.getDepartment().getId());
			//Get Max Beneficiaries
			int b = d.getBeneficiaries();
			System.out.println(d.getName());
			//Get department students
			List<Student> depStudents = d.getStudents();
			System.out.println("SIZE " + depStudents.size());
//			System.out.println(depStudents.get(0).getApplication().getPoints());
			LinkedHashMap<Integer, Integer> studentsPoints = new LinkedHashMap<>();
			LinkedHashMap<Integer, Integer> sorted = new LinkedHashMap<>();
			//For each student in list
			for (Student a : depStudents) {
				//Put in hashmap student's Id and student's Application Points
				studentsPoints.put(a.getId(), a.getApplication().getPoints());
				
			}
			//Order List Descending
			System.out.println("BEFORE " +studentsPoints);
			studentsPoints.entrySet()
		    .stream()
		    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
		    .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
			System.out.println("AFTER " + sorted);
			
			//Finally
			int studentRanking = 1;
			for (Integer key : sorted.keySet()) {
				if (key == s.getId()) {
					System.out.println("Found");
					break;
				}
				studentRanking++;
			}
			System.out.println("Stopped at " + studentRanking);
			String isAllowedForAlimentation;
			if (studentRanking <= b) {
				isAllowedForAlimentation = "You are allowed to get Ailment";
				System.out.println("DIKAIOUSE");
			} else {
				isAllowedForAlimentation = "You are NOT allowed to get Ailment";
				System.out.println("Oxi");
			}
			
			//Update Ranking
			s.setRanking(studentRanking);
			studentDAO.saveStudent(s);
			
			
			Map<String,Integer> returnResults =new HashMap<>();
			returnResults.put( isAllowedForAlimentation, studentRanking);
			return returnResults;
		}

		//FOR MAKING REST CLIENT
//		https://howtodoinjava.com/spring-boot2/resttemplate/spring-restful-client-resttemplate-example/
//		https://www.baeldung.com/rest-template
}
