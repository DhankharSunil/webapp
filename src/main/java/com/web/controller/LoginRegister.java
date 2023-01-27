package com.web.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.commen.controller.EncryptionUtil;
import com.web.application.UserLoginRegister;
import com.webapp.service.LoginRegisterService;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web.application.IplData;
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
					session.setAttribute("Message143", s1);
					session.setAttribute("alertType", "success");
					userReturn = "redirect:/registerpage";
				}else {
					try {
						boolean result = loginRegisterService.insertuserdetails(user);
						System.out.println("Result Status :::: "+result);
						if(result) {
							session.setAttribute("A143", "2");
							session.setAttribute("Message143", "Register Successfull, Login");
							session.setAttribute("alertType", "success");
							userReturn = "loginpage";
						}else {
							session.setAttribute("A143", "2");
							session.setAttribute("Message143", "Register Failed, Please Try Again");
							session.setAttribute("alertType", "success");
							userReturn = "Registerpage";
						}
					}catch (Exception e) {
						System.out.println("controller "+e);
					}
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
			boolean accountcheck = loginRegisterService.checkAccountExist(user);
			if(accountcheck) {
				boolean passCheck = loginRegisterService.getpassDetails(user);
				if(passCheck) {
					session.setAttribute("username", user.getUname());
					System.out.println(session.getAttribute("username"));
					returnType = "homepage";
				}else {
					session.setAttribute("A143", "2");
					session.setAttribute("Message143", "Enter Currect Password");
					session.setAttribute("alertType", "success");
					returnType = "redirect:/login";
				}
			}else {
				session.setAttribute("A143", "2");
				session.setAttribute("Message143", "This Account Is Disble");
				session.setAttribute("alertType", "success");
				returnType = "redirect:/login";
			}
		}else {
			session.setAttribute("A143", "2");
			session.setAttribute("Message143", "Enter Valid E-mail");
			session.setAttribute("alertType", "success");
			returnType = "redirect:/login";
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
			String message = "Hello , Your WebApp OTP - "+otp;
	        String subject = "OTP Configretion";
	        String to = user.getUname();
	        String from = "dhankharss476@gmail.com";
	        sendMail(message, subject, to, from);
		}else {
			request.getSession().setAttribute("A143", "2");
			request.getSession().setAttribute("Message143", "Enter Currect Email");
			request.getSession().setAttribute("alertType", "success");
			return "forgetpass";
		}
		return "newpass";
	}
	private static void sendMail(String message, String subject, String to, String from) {
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("dhankharss476@gmail.com", "uamvzaluewzvguii");
			}
		});
		session.setDebug(true);
		MimeMessage mime = new MimeMessage(session);
		try {
			mime.setFrom(from);
			mime.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mime.setSubject(subject);
			mime.setText(message);
			Transport.send(mime);
			System.out.println("send Massage ");
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	@RequestMapping(value = "/password-change",method = RequestMethod.POST)
	public String passwordchange(HttpServletRequest request, HttpServletResponse responce) {
		UserLoginRegister user = new UserLoginRegister();
		String otpis = request.getParameter("mailotp");
		if(request.getParameter("password") != null) {
			user.setPass(EncryptionUtil.decryptPassJS(request.getParameter("password")));
		}
		if(request.getParameter("confirm") != null) {
			user.setCopass(EncryptionUtil.decryptPassJS(request.getParameter("confirm")));
		}
		if(request.getSession().getAttribute("usercode") != null) {
			user.setUname((String) request.getSession().getAttribute("usercode"));
		}
		System.out.println("Change pass "+user.getUname());
		if(request.getSession().getAttribute("otp").equals(otpis)) {
			boolean passstatus = loginRegisterService.updatepass(user);
			if(passstatus) {
				request.getSession().setAttribute("A143", "2");
				request.getSession().setAttribute("Message143", "update password");
				request.getSession().setAttribute("alertType", "success");
				return"loginpage";
			}else {
				request.getSession().setAttribute("A143", "2");
				request.getSession().setAttribute("Message143", "Somthing Went Wrong!");
				request.getSession().setAttribute("alertType", "success");
				return "forgetpass";
			}
		}else {
			request.getSession().setAttribute("A143", "2");
			request.getSession().setAttribute("Message143", "OTP Not Matched!");
			request.getSession().setAttribute("alertType", "success");
		}
		return "newpass";
	}
	@RequestMapping(value = "/deletedaccount",method = RequestMethod.POST)
	public String deletedaccount(HttpServletRequest request, HttpServletResponse response) {
		UserLoginRegister user = new UserLoginRegister();
		String usermail = request.getSession().getAttribute("username").toString();
		System.out.println(usermail);
		user.setUname(usermail);
		boolean delAccount = false;
		try {
			delAccount = loginRegisterService.deleteAccountid(user);
		}catch (Exception e) {
			System.out.println("delete Account Exception "+e);
		}
		if(delAccount) {
		request.getSession().setAttribute("A143", "2");
		request.getSession().setAttribute("Message143", "Account Deleted Successfull");
		request.getSession().setAttribute("alertType", "success");
		return "loginpage";
		}else {
			request.getSession().setAttribute("A143", "2");
			request.getSession().setAttribute("Message143", "Account Not Deleted Now , Try Again");
			request.getSession().setAttribute("alertType", "success");
			return "homepage";
		}
	}
	
	
	@RequestMapping(value = "/upload-provision",method=RequestMethod.POST)
	public String uploadGncdValidClassification(@RequestParam("file")MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
		HttpSession session=request.getSession();
		try {
			String filename=multipartFile.getOriginalFilename();
			File file = new File(System.getProperty("user.dir")+File.separator+multipartFile.getOriginalFilename());			
	    	file.createNewFile();	
	    	multipartFile.transferTo(file);
		//	String currentDate=RISLDateUtility.getCurrentDateTime();
			String fileType="";		
			if(!filename.isEmpty() && filename.contains("xlsx")) {			
				if(request.getParameter("fileType")!=null) {
					fileType=request.getParameter("fileType").toString();
			}								
				List<IplData> ipl=LoginRegisterService.readExcel(file,fileType);	
				
				System.out.println(ipl);
				session.setAttribute("A143", "2");
				session.setAttribute("Message143", "File Upload Successfully!");
				session.setAttribute("alertType", "success");
				return "homepage";
		}else {
			session.setAttribute("A143", "2");
			session.setAttribute("Message143", "Required file is not in excel format!");
			session.setAttribute("alertType", "danger");
			return "homepage";
		}
	}catch (Exception e) {
			session.setAttribute("A143", "2");
			session.setAttribute("Message143", "Error while uploading the file!");
			session.setAttribute("alertType", "danger");
			System.out.println("exception in DataProcessing.uploadIpldata "+e);
	}
		return "homepage";
	}
	
	
}