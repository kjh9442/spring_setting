package com.kimjeaho.test;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kimjeaho.dao.testDAO;
import com.kimjeaho.vo.testVO;

	


@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model) {
		return "redirect:home";
	}
	
	@RequestMapping(value = "/home")
	public String home(HttpServletRequest request, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value = "/insert")
	public String insert(HttpServletRequest request, Model model) {
		
		testDAO dao = sqlSession.getMapper(testDAO.class);
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application_ctx.xml");
		testVO testVO = ctx.getBean("vo", testVO.class);
		testVO.setId(request.getParameter("id"));
		testVO.setPw(request.getParameter("pw"));
		dao.insert(testVO);
		ctx.close();
		return "redirect:home";
	}
	
	
}
