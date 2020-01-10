package gr.hua.dit.dao;

import java.util.List;

import gr.hua.dit.entities.Application;

public interface ApplicationDAO {
	public void saveApplication(Application application);

	public List<Application> getApplications();

	public Application getApplication(int id);

	public void deleteApplication(int id);
	
	public List<Application> getUnapprovedApplications();
	
	public void approveApplication(Application application);
}
