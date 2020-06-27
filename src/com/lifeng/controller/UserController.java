package com.lifeng.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeng.entity.User;
import com.lifeng.service.UserService;
import com.lifeng.util.Unicode;




@Controller
public class UserController {
	@Autowired
	private UserService userService;

	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}	

	//���ڷ�����ҳ
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	//���ڵ�¼
	@RequestMapping("/login")
	@ResponseBody
	public Object login(HttpSession session,HttpServletResponse response,String username,String password,String autologin) {
		System.out.println("username:"+username+" password:"+password);
		User user = userService.login(username, password);
		Map<String,Object> map = new HashMap<String,Object>();
		try {			
			//��¼�ɹ�,����cookie���Ա��´��Զ���¼
			if(user != null) {
				session.setAttribute("user", user);
				if(autologin.equals("true")) {
					Cookie cookie = new Cookie("user", new Unicode().toCookieUnicode(user.getUsername()) + "==" +new Unicode().toCookieUnicode(user.getPassword()));
					cookie.setPath("/");
					cookie.setMaxAge(1000*60*60*24);//24Сʱ
					response.addCookie(cookie);
				}			
				map.put("state","true");
				map.put("user", user);				
			}else {
				map.put("state","false");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	//�����˳���¼
	@RequestMapping("/logout")
	public void logout(HttpSession session,HttpServletResponse response) throws IOException, ServletException {
		
		session.removeAttribute("user");
		//���cookie
		Cookie cookie = new Cookie("user", "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.sendRedirect("index");
	
	}
	
}
