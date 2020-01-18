package gr.hua.dit.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import gr.hua.dit.entities.Application;
import gr.hua.dit.entities.Authorities;
import gr.hua.dit.entities.Department;
import gr.hua.dit.entities.Employee;
import gr.hua.dit.entities.Form;
import gr.hua.dit.entities.Student;
import gr.hua.dit.entities.User;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private DepartmentDAO departmentDAO;

	@Autowired
	private ApplicationDAO applicationDAO;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// CHANGE THIS TO false TO HAVE DATA IN DB
	boolean alreadySetup = true;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Session currentSession = sessionFactory.getCurrentSession();
		if (alreadySetup) {

			System.out.println("Already Setted Up");

			return;
		} else {
			/////// Departments
			Department d1 = new Department();
			d1.setName("Πληροφορική");
			d1.setLocation("Αθήνα");
			d1.setTotalStudents(5);
			departmentDAO.saveDepartment(d1);

			Department d2 = new Department();
			d2.setName("Διαιτολογία");
			d2.setLocation("Αθήνα");
			d2.setTotalStudents(2);
			departmentDAO.saveDepartment(d2);

			Department d3 = new Department();
			d3.setName("Γεωγραφία");
			d3.setLocation("Αθήνα");
			d3.setTotalStudents(2);
			departmentDAO.saveDepartment(d3);

			Department d4 = new Department();
			d4.setName("Οικιακή Οικονομία");
			d4.setLocation("Αθήνα");
			d4.setTotalStudents(2);
			departmentDAO.saveDepartment(d4);

			// Roles
			Authorities sAuth = new Authorities();
			sAuth.setAuthority("ROLE_STUDENT");
			currentSession.save(sAuth);

			Authorities cAuth = new Authorities();
			cAuth.setAuthority("ROLE_CHIEF");
			currentSession.save(cAuth);

			Authorities aAuth = new Authorities();
			aAuth.setAuthority("ROLE_ADMIN");
			currentSession.save(aAuth);

			Authorities eAuth = new Authorities();
			eAuth.setAuthority("ROLE_EMP");
			currentSession.save(eAuth);

			// Users Roles
			List<Authorities> stu_auths = new ArrayList<>();
			stu_auths.add(sAuth);
			List<Authorities> emp_auths = new ArrayList<>();
			emp_auths.add(eAuth);
			List<Authorities> chief_auths = new ArrayList<>();
			chief_auths.add(cAuth);
			chief_auths.add(eAuth);
			List<Authorities> admin_auths = new ArrayList<>();
			admin_auths.add(aAuth);

			// Passwords
			String studPass = passwordEncoder.encode("it@123");
			String empPass = passwordEncoder.encode("emp@123");
			String adminPass = passwordEncoder.encode("admin@123");

			// New Admin
			User u = new User();
			u.setAuthorities(admin_auths);
			u.setEnabled(true);
			u.setUsername("admin");
			u.setPassword(adminPass);
			u.setFirstName("Ανάργυρος");
			u.setLastName("Τσαδίμας");
			userDAO.saveAdmin(u);

			// New Employees & Chief
			Employee c = new Employee();
			c.setAuthorities(chief_auths);
			c.setEnabled(true);
			c.setUsername("chief");
			c.setPassword(empPass);
			c.setFirstName("Γιώργος");
			c.setLastName("Καραγιώργος");
			c.setChief(true);
			List<Department> chiefDeps = new ArrayList<>();
			chiefDeps.add(d1);
			c.setDepartments(chiefDeps);
			employeeDAO.saveEmployee(c);

			Employee e1 = new Employee();
			e1.setAuthorities(emp_auths);
			e1.setEnabled(true);
			e1.setUsername("emp1");
			e1.setPassword(empPass);
			e1.setFirstName("Τάσος");
			e1.setLastName("Πασχάλης");
			e1.setChief(false);
			List<Department> e1Deps = new ArrayList<>();
			e1Deps.add(d2);
			e1Deps.add(d3);
			e1.setDepartments(e1Deps);
			employeeDAO.saveEmployee(e1);

			Employee e2 = new Employee();
			e2.setAuthorities(emp_auths);
			e2.setEnabled(true);
			e2.setUsername("emp2");
			e2.setPassword(empPass);
			e2.setFirstName("Αποστόλης");
			e2.setLastName("Παππάς");
			e2.setChief(false);
			List<Department> e2Deps = new ArrayList<>();
			e2Deps.add(d4);
			e2.setDepartments(e2Deps);
			employeeDAO.saveEmployee(e2);

			// New Students
			Student s1 = new Student();
			s1.setAuthorities(stu_auths);
			s1.setEnabled(true);
			s1.setUsername("it2131");
			s1.setPassword(studPass);
			s1.setFirstName("Μάκης");
			s1.setLastName("Κάτι1");
			s1.setPhone("changeInfo!");
			s1.setActivated(false);
			s1.setDepartment(d1);
			studentDAO.saveStudent(s1);
			
			Form f3 = new Form();
			f3.setAnnualIncome(9000);
			f3.setParentalStatus(true);
			f3.setResdence("Αθήνα");
			f3.setSiblingStudents(0);
			Application ap3 = new Application();
			ap3.setCreationDate(LocalDateTime.now());
			ap3.setApproved(false);
			ap3.setPoints(0);
			ap3.setApplicationForm(f3);
			Student s2 = new Student();
			s2.setAuthorities(stu_auths);
			s2.setEnabled(true);
			s2.setUsername("it2132");
			s2.setPassword(studPass);
			s2.setFirstName("Λάκης");
			s2.setLastName("Κάτι2");
			s2.setPhone("changeInfo!");
			s2.setActivated(false);
			s2.setDepartment(d1);
			s2.setApplication(ap3);
			ap3.setCreatedBy(s2);
			studentDAO.saveStudent(s2);
			
			Form f4 = new Form();
			f4.setAnnualIncome(12000);
			f4.setParentalStatus(true);
			f4.setResdence("Θεσσαλονίκη");
			f4.setSiblingStudents(2);
			Application ap4 = new Application();
			ap4.setCreationDate(LocalDateTime.now());
			ap4.setApproved(false);
			ap4.setPoints(0);
			ap4.setApplicationForm(f4);
			Student s3 = new Student();
			s3.setAuthorities(stu_auths);
			s3.setEnabled(true);
			s3.setUsername("it2133");
			s3.setPassword(studPass);
			s3.setPhone("changeInfo!");
			s3.setFirstName("Τάκης");
			s3.setLastName("Κάτι3");
			s3.setActivated(false);
			s3.setDepartment(d1);
			s3.setApplication(ap4);
			ap4.setCreatedBy(s3);
			studentDAO.saveStudent(s3);

			Form f5 = new Form();
			f5.setAnnualIncome(20000);
			f5.setParentalStatus(true);
			f5.setResdence("Αθήνα");
			f5.setSiblingStudents(3);
			Application ap5 = new Application();
			ap5.setCreationDate(LocalDateTime.now());
			ap5.setApproved(false);
			ap5.setPoints(0);
			ap5.setApplicationForm(f5);
			Student s4 = new Student();
			s4.setAuthorities(stu_auths);
			s4.setEnabled(true);
			s4.setUsername("it2134");
			s4.setPassword(studPass);
			s4.setFirstName("Άκης");
			s4.setLastName("Κάτι4");
			s4.setPhone("changeInfo!");
			s4.setActivated(false);
			s4.setDepartment(d1);
			s4.setApplication(ap5);
			ap5.setCreatedBy(s4);
			studentDAO.saveStudent(s4);

			Student s11 = new Student();
			s11.setAuthorities(stu_auths);
			s11.setEnabled(true);
			s11.setUsername("it21311");
			s11.setPassword(studPass);
			s11.setPhone("changeInfo!");
			s11.setFirstName("Χάρης");
			s11.setLastName("Κάτι11");
			s11.setActivated(false);
			s11.setDepartment(d1);
			studentDAO.saveStudent(s11);
			
			Student s5 = new Student();
			s5.setAuthorities(stu_auths);
			s5.setEnabled(true);
			s5.setUsername("it2135");
			s5.setPassword(studPass);
			s5.setFirstName("Γεώργιος");
			s5.setPhone("changeInfo!");
			s5.setLastName("Κάτι5");
			s5.setActivated(false);
			s5.setDepartment(d2);
			studentDAO.saveStudent(s5);

			Student s6 = new Student();
			s6.setAuthorities(stu_auths);
			s6.setEnabled(true);
			s6.setUsername("it2136");
			s6.setPhone("changeInfo!");
			s6.setPassword(studPass);
			s6.setFirstName("Αποστόλης");
			s6.setLastName("Κάτι6");
			s6.setActivated(false);
			s6.setDepartment(d2);
			studentDAO.saveStudent(s6);

			Student s7 = new Student();
			s7.setAuthorities(stu_auths);
			s7.setEnabled(true);
			s7.setUsername("it2137");
			s7.setPassword(studPass);
			s7.setFirstName("Χρήστος");
			s7.setPhone("changeInfo!");
			s7.setLastName("Κάτι7");
			s7.setActivated(false);
			s7.setDepartment(d3);
			studentDAO.saveStudent(s7);

			Student s8 = new Student();
			s8.setAuthorities(stu_auths);
			s8.setEnabled(true);
			s8.setUsername("it2138");
			s8.setPassword(studPass);
			s8.setFirstName("Αναστάσης");
			s8.setLastName("Κάτι8");
			s8.setActivated(false);
			s8.setDepartment(d3);
			studentDAO.saveStudent(s8);


			Form f1 = new Form();
			f1.setAnnualIncome(0);
			f1.setParentalStatus(false); // false = both dead
			f1.setResdence("Αθήνα");
			f1.setSiblingStudents(0);
			Application ap1 = new Application();
			ap1.setCreationDate(LocalDateTime.now());
			ap1.setApproved(false);
//			ap1.setCreatedBy(s9);
			ap1.setPoints(0);
			ap1.setApplicationForm(f1);
//			applicationDAO.saveApplication(ap1);
			Student s9 = new Student();
			s9.setAuthorities(stu_auths);
			s9.setEnabled(true);
			s9.setUsername("it2139");
			s9.setPassword(studPass);
			s9.setFirstName("Νίκος");
			s9.setLastName("Κάτι9");
			s9.setPhone("changeInfo!");
			s9.setActivated(true);
			s9.setDepartment(d4);
			s9.setApplication(ap1);
			ap1.setCreatedBy(s9);
			studentDAO.saveStudent(s9);

			Form f2 = new Form();
			f2.setAnnualIncome(10000);
			f2.setParentalStatus(true); // true = at least one of them alive
			f2.setResdence("Θεσσαλονίκη");
			f2.setSiblingStudents(2);
			Application ap2 = new Application();
			ap2.setCreationDate(LocalDateTime.now());
			ap2.setApproved(false);
//			ap2.setCreatedBy(s10);
			ap2.setPoints(0);
			ap2.setApplicationForm(f2);
//			applicationDAO.saveApplication(ap2);
			Student s10 = new Student();
			s10.setAuthorities(stu_auths);
			s10.setEnabled(true);
			s10.setUsername("it21310");
			s10.setPassword(studPass);
			s10.setFirstName("Βαγγέλης");
			s10.setPhone("changeInfo!");
			s10.setLastName("Κάτι10");
			s10.setActivated(true);
			s10.setDepartment(d4);
			s10.setApplication(ap2);
			ap2.setCreatedBy(s10);
			studentDAO.saveStudent(s10);
			

			System.out.println("Done Settin Up!!!");

		}

	}

}