package com.lifeng.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lifeng.entity.User;
import com.lifeng.service.UserService;
import com.lifeng.util.Unicode;




public class AutoLoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
	
		Cookie[] cookies = request.getCookies();		
		String username = "";
		String password = "";
		//Ϊ����������������,ʹ���˹�����Unicode,�ڰ�com.bookshop.util��
		Unicode unicode = new Unicode();
		//����cookie
		if(cookies !=null) {
			for(int i = 0; i <cookies.length; i++) {
				if(cookies[i].getName().equals("user")) {
					username = unicode.toCookieString(cookies[i].getValue().split("==")[0]);
					password = unicode.toCookieString(cookies[i].getValue().split("==")[1]);
					break;
				}
			}
			if((username !=null || username !="") && (password !=null || password !="")) {
				//��֤��cookie�л�ȡ�û����������Ƿ�Ϸ�
				ApplicationContext applicationContext=
						new ClassPathXmlApplicationContext("applicationContext.xml");
				UserService userService=applicationContext.getBean("userService",UserService.class);
				User user=userService.login(username, password);
				//����ǺϷ��û�.������session
				if(user != null) {
					request.getSession().setAttribute("user", user);
					
				}
			}
		}
		return true;		
	}	
}
