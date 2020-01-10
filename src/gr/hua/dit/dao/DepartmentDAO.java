package gr.hua.dit.dao;
import java.util.List;

import gr.hua.dit.entities.Department;

public interface DepartmentDAO {
	public void saveDepartment(Department department);
	
	public List<Department> getDepartments();
	
	public Department getDepartment(int id);
	
	public void deleteDepartment(int id);
	
	public void updateDepartmentsBeneficiaries();
}
