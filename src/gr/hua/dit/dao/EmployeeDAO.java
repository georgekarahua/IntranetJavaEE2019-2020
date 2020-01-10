package gr.hua.dit.dao;

import gr.hua.dit.entities.Employee;

public interface EmployeeDAO {
	public void saveEmployee(Employee employee);
	
	public Employee getEmployee(int id);

	public void deleteEmployee(int id);
	
	
}
