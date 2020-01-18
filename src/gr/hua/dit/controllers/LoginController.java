package gr.hua.dit.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gr.hua.dit.dao.ApplicationDAO;
import gr.hua.dit.dao.DepartmentDAO;
import gr.hua.dit.dao.StudentDAO;
import gr.hua.dit.dao.UserDAO;
import gr.hua.dit.entities.Application;
import gr.hua.dit.entities.Student;
import gr.hua.dit.entities.User;

////Add this to spring-mvc-servlet to work
//<mvc:view-controller path="/login" view-name="login"/>

@Controller
public class LoginController {
	@Autowired
	private UserDAO userDao;

	@GetMapping("/")
	@Transactional
	public String index(Model model, Principal principal) {
		User user = userDao.findByUserName(principal.getName());
		System.out.println(user.toString());
	
		String role = user.getAuthorities().get(0).getAuthority();
		System.out.println(role);
		
		
		model.addAttribute("welcomemessage", "Welcome " + user.getFirstName() + " " + user.getLastName());
		model.addAttribute("roles", "Your Role: " + role);
		
		if (role.equals("ROLE_ADMIN")) {
			return "index3"; //na ftiaxw jsp index3
		} else if (role.equals("ROLE_CHIEF")){ 
			return "finaletoChief"; //deftero
		} else if (role.equals("ROLE_EMP")) {
			return "finaletoEmployee"; //prwto
		}
		return null;
	}

	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout=true";
	}
}
