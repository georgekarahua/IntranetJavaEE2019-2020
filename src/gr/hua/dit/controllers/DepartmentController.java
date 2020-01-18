package gr.hua.dit.controllers;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.hua.dit.dao.DepartmentDAO;
import gr.hua.dit.entities.Department;

@Controller
@RequestMapping("/department")



public class DepartmentController {
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@GetMapping("/ledepart")
	@Transactional
	public String getDepartment(Model model, Principal principal) {
			
		//List of all unactivate students of employee's departments
		List<Department> departmentua = departmentDAO.getDepartments();
		model.addAttribute("departments", departmentua);
	
		return "finaletoChiefDep";
	}
	
	@Transactional
	@RequestMapping(value = "/updateBeneficiariesOfDepartments", method = RequestMethod.GET)
	public String updateBeneficiariesOfDepartments() {
		
		departmentDAO.updateDepartmentsBeneficiaries();
		
		return "redirect:/department/ledepart";
	}

}
