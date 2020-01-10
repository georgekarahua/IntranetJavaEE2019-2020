package gr.hua.dit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entities.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveEmployee(Employee employee) {
		// get current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();

				if (employee.getId() != 0) {
				// update the student
					currentSession.update(employee);
				}
				else {
					// save the student
				currentSession.save(employee);
				}

	}

	@Override
	public Employee getEmployee(int id) {
		// get current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();

				// get and return Student
				Employee employee = currentSession.get(Employee.class, id);
				return employee;
	}

	@Override
	public void deleteEmployee(int id) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// find the student
		Employee employee = currentSession.get(Employee.class, id);

		// delete student
		currentSession.delete(employee);
	}

}
