package com.web.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
					session.setAttribute("massege", s1);
					session.setAttribute("alertType", "success");
					userReturn = "redirect:/registerpage";
				}else {
					boolean result = loginRegisterService.insertuserdetails(user);
					System.out.println("Result Status :::: "+result);
					session.setAttribute("A143", "2");
					session.setAttribute("massege", "Register Successfull, Login");
					session.setAttribute("alertType", "success");
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
				session.setAttribute("massege", "Enter Currect Password");
				session.setAttribute("alertType", "success");
				returnType = "loginpage";
			}
		}else {
			session.setAttribute("A143", "2");
			session.setAttribute("massege", "Enter Valid E-mail");
			session.setAttribute("alertType", "success");
			returnType = "loginpage";
		}
		return returnType;
	}
	
	@RequestMapping(value = "/passreset",method = RequestMethod.GET)
	public String passreset(HttpServletRequest request, HttpServletResponse responce) {
		System.out.println("reset pass");
		return "forgetpass";
	}
	
	@RequestMapping(value = "/otpsend",method = RequestMethod.POST)
	public String otpsend(HttpServletRequest request, HttpServletResponse responce, ModelMap map) {
		UserLoginRegister user = new UserLoginRegister();
		if(request.getParameter("email") != null) {
			user.setUname(request.getParameter("email"));
		}
		boolean statusmail = loginRegisterService.emailExistCheck(user);
		if(statusmail) {
			request.getSession().setAttribute("usercode", user.getUname());
			int len = 6;
			String otp = "";
			int ranNo;
			for (int i = 0; i < len; i++) {
				ranNo = new Random().nextInt(9);
				otp = otp.concat(Integer.toString(ranNo));
			}
			request.getSession().setAttribute("otp", otp);
			map.addAttribute("sendotp", otp);
			map.addAttribute("usercode", user.getUname());
			System.out.println(otp);
		}else {
			request.getSession().setAttribute("A143", "2");
			request.getSession().setAttribute("massege", "Enter Currect Email");
			request.getSession().setAttribute("alertType", "success");
			return "forgetpass";
		}
		return "newpass";
	}
	
	@RequestMapping(value = "/password-change",method = RequestMethod.POST)
	public String passwordchange(HttpServletRequest request, HttpServletResponse responce) {
		UserLoginRegister user = new UserLoginRegister();
		if(request.getParameter("password") != null) {
			user.setPass(request.getParameter("password"));
		}
		if(request.getParameter("confirm") != null) {
			user.setCopass(request.getParameter("confirm"));
		}
		if(request.getSession().getAttribute("username") != null) {
			user.setUname((String) request.getSession().getAttribute("username"));
		}
		if(user.getPass().equals(user.getCopass())) {
			boolean passstatus = loginRegisterService.updatepass(user);
		if(passstatus) {
			request.getSession().setAttribute("A143", "2");
			request.getSession().setAttribute("massege", "update password");
			request.getSession().setAttribute("alertType", "success");
			return"loginpage";
		}else {
			request.getSession().setAttribute("A143", "2");
			request.getSession().setAttribute("massege", "Enter Currect Email");
			request.getSession().setAttribute("alertType", "success");
			return "forgetpass";
		}
		}
		return "mailotp";
	}
}