package com.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.commen.controller.EncryptionUtil;
import com.web.application.UserLoginRegister;
import com.webapp.service.LoginRegisterService;

@Controller
public class LoginRegister {
	
	@Autowired
	LoginRegisterService loginRegisterService;
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
	public String login(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In Home");
		return "loginpage";
	//	return "testingCode";
	}
	
	@RequestMapping(value = "/registerpage", method = {RequestMethod.POST,RequestMethod.GET})
	public String registerpage() {
		System.out.println("Welcome to Register");
		return "Registerpage";
	}
	
	@RequestMapping(value = "/logout", method = {RequestMethod.POST,RequestMethod.GET})
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("you are Logout");
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		System.out.println(session.getAttribute("username"));
		System.out.println("In logout");
		return "logout";
	}
	
	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public String userRegis(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserLoginRegister user = new UserLoginRegister();
		System.out.println("Register");
		String userReturn = "";
		if (request.getParameter("fname") != null) {
			user.setFname(request.getParameter("fname").toString());
		}
		if (request.getParameter("lname") != null) {
			user.setLname(request.getParameter("lname").toString());
		}
		if (request.getParameter("email") != null) {
			user.setUname(request.getParameter("email").toString());
		}
		if (request.getParameter("password") != null) {
			user.setPass(EncryptionUtil.decryptPassJS(request.getParameter("password").trim()));
		}
		if (request.getParameter("confirm") != null) {
			user.setCopass(EncryptionUtil.decryptPassJS(request.getParameter("password").trim()));
		}
		if(user.getPass().equals(user.getCopass())) {
			try {
				boolean emailStatus = false;
				try {
					emailStatus = loginRegisterService.emailExistCheck(user);
				} catch (Exception e) {
					System.out.println("email Check "+e);
				}
				System.out.println("Email Status = "+emailStatus);
				if(emailStatus) {
					String s1 = "";
					s1 = " </strong> Email Already Exist !! </strong>";
					session.setAttribute("A143", "2");
					session.setAttribute("usrMsg", s1);
					session.setAttribute("alertType", "success");
					userReturn = "redirect:/registerpage";
				}else {
					boolean result = loginRegisterService.insertuserdetails(user);
					System.out.println("Result Status :::: "+result);
					userReturn = "loginpage";
				}
			}catch (Exception e) {
				System.out.println("controller "+e);
			}
		}else {
			System.out.println("Password Can not Matched");
		}
		return userReturn;
	}
	
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public String loginuser(HttpServletRequest request, HttpServletResponse response) {
		String returnType = "";
		UserLoginRegister user = new UserLoginRegister();
		HttpSession session = request.getSession();
		if(request.getParameter("uname") != null) {
			user.setUname(request.getParameter("uname"));
		}
		if(request.getParameter("psw") != null) {
			user.setPass(EncryptionUtil.decryptPassJS(request.getParameter("psw")));
		}
		boolean unameCheck = loginRegisterService.getloginDetails(user);
		if(unameCheck) {
			boolean passCheck = loginRegisterService.getpassDetails(user);
			if(passCheck) {
				session.setAttribute("username", user.getUname());
				System.out.println(session.getAttribute("username"));
				returnType = "homepage";
			}else {
				session.setAttribute("A143", "2");
				session.setAttribute("loginTest", "Enter Currect Password");
				session.setAttribute("alertType", "success");
				returnType = "loginpage";
			}
		}else {
			session.setAttribute("A143", "2");
			session.setAttribute("uploadTest", "Enter Valid E-mail");
			session.setAttribute("alertType", "success");
			returnType = "loginpage";
		}
		return returnType;
	}
}