package gr.hua.dit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entities.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
			.createQuery("from User u where u.username=:username")
			.setParameter("username", username)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void saveAdmin(User user) {
		Session currentSession = sessionFactory.getCurrentSession();

		if (user.getId() != 0) {
		// update the student
			currentSession.update(user);
		}
		else {
			// save the student
		currentSession.save(user);
		}
		
	}

}
