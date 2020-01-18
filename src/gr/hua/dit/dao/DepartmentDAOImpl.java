package gr.hua.dit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entities.Department;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

	// inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveDepartment(Department department) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		if (department.getId() != 0) {
			// update the department
			currentSession.update(department);
		} else {
			// save the department
			currentSession.save(department);
		}

	}

	@Override
	public List<Department> getDepartments() {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Department> query = currentSession.createQuery("from Department", Department.class);

		// execute the query and get the results list
		List<Department> departments = query.getResultList();

		// return the results
		return departments;
	}

	@Override
	public Department getDepartment(int id) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// get and return Student
		Department department = currentSession.get(Department.class, id);
		return department;
	}

	@Override
	public void deleteDepartment(int id) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// find the student
		Department department = currentSession.get(Department.class, id);

		// delete student
		currentSession.delete(department);

	}

	@Override
	public void updateDepartmentsBeneficiaries() {
		Session currentSession = sessionFactory.getCurrentSession();
		List<Department> deps = getDepartments();
		
		for(Department dep : deps) {
			System.out.println(dep.getName() + " " + dep.getTotalStudents());
			int beneficiaries = (int)Math.round((dep.getTotalStudents()*80)/100);
			System.out.println("New Beneficiaries: " + beneficiaries);
			dep.setBeneficiaries(beneficiaries);
			saveDepartment(dep);
		}
	}

}
