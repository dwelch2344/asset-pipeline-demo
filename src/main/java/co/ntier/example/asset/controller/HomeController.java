package co.ntier.example.asset.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.ntier.example.asset.model.SimpleUser;
import co.ntier.example.asset.svc.UserService;

@Controller
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class.getName());
	
	@Inject
	private UserService userService;

	@RequestMapping({"/", "/home"})
	public String getHome(Model model) {
		List<SimpleUser> users = userService.findAll();
		model.addAttribute("users", users);
		return "home";
	}
	
	@RequestMapping({"/about"})
	public String getAbout(Model model) {
		return "about";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getRegister(Model model) {
		model.addAttribute("bean", new RegisterBean());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String postRegister(Model model, @ModelAttribute("bean") @Valid RegisterBean bean, BindingResult result) {
		boolean invalid = result.hasErrors();
		if( invalid ){
			logger.info("Failed registering");
			return "register";
		}
		
		SimpleUser user = userService.create( bean.getName(), bean.getEmail(), bean.getPassword());
		return "redirect:/home?user=" + user.getId();
	}
	
	@RequestMapping("/secure/account")
	public String getSecureTest(Model model) {
		return "account";
	}
	
	public static class RegisterBean{
		@NotNull @NotEmpty
		private String name;
		
		@NotNull @NotEmpty @Email
		private String email;
		
		@Length(min=6)
		private String password;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
	
}
