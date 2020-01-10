package gr.hua.dit.dao;
import java.util.List;

import gr.hua.dit.entities.Student;

public interface StudentDAO {

	public void saveStudent(Student student);
	
	public Student getStudent(int id);

	public  List<Student> getStudents();
	
	public void deleteStudent(int id);
	
	public List<Student> getUnactiveStudentsOfDepartement(int depId);
	
}
