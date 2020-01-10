package gr.hua.dit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entities.Application;
import gr.hua.dit.entities.Form;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {
	
	// inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveApplication(Application application) {
		Session currentSession = sessionFactory.getCurrentSession();

		if (application.getId() != 0) {
			// update the department
			currentSession.update(application);
		} else {
			// save the department
			currentSession.save(application);
		}
	}

	@Override
	public List<Application> getApplications() {
		// get current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();

				// create a query
				Query<Application> query = currentSession.createQuery("from Application", Application.class);

				// execute the query and get the results list
				List<Application> applications = query.getResultList();

				// return the results
				return applications;
	}

	@Override
	public Application getApplication(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get and return Student
		Application application = currentSession.get(Application.class, id);
		return application;
	}

	@Override
	public void deleteApplication(int id) {
		// get current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();

				// find the student
				Application application = currentSession.get(Application.class, id);

				// delete student
				currentSession.delete(application);

	}

	@Override
	public List<Application> getUnapprovedApplications() {
		Session currentSession = sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		Query<Application> query = currentSession.createQuery("from Application a where a.approved=false");

		// execute the query and get the results list
		List<Application> application = query.getResultList();
		
		return application;
	}

	@Override
	public void approveApplication(Application application) {
		Session currentSession = sessionFactory.getCurrentSession();
		Form form = application.getApplicationForm();
		int points = 0;
	
		//Check student's parents if both unemployed
		if (!form.isParentalStatus()) {
			points = 1000;
		} else {
			//Check student's family annual income
			if (form.getAnnualIncome() < 10000) {
				points += 100;
			} else if (form.getAnnualIncome() <= 15000) {
				points += 30;
			}
			//Check student's residence
			String depLoc = application.getCreatedBy().getDepartment().getLocation();
			System.out.println(depLoc);
			if (!depLoc.equals(form.getResdence())) {
				points += 50;
			}
			//Student's siblings that are students
			points += 20 * form.getSiblingStudents();
		}
		//Set approved and points
		application.setApproved(true);
		application.setPoints(points);
		//Update Application
		saveApplication(application);
	}

}
