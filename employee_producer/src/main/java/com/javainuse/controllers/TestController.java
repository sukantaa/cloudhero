package com.javainuse.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javainuse.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class TestController {

	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ModelAndView landingPage(HttpServletRequest request,HttpServletResponse response) {
//
//		System.out.println("Inside landingPage");
//		
//		try {
//			response.sendRedirect(request.getContextPath()+"/test-app/index.html");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	public Employee firstPage() {

		System.out.println("Inside firstPage");
		
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		
		if(emp.getName().equalsIgnoreCase("emp1"))
			throw new RuntimeException();

		return emp;
	}

	public Employee getDataFallBack() {
		
		System.out.println("Inside fallback");

		
		Employee emp = new Employee();
		emp.setName("fallback-emp1");
		emp.setDesignation("fallback-manager");
		emp.setEmpId("fallback-1");
		emp.setSalary(3000);

		return emp;
		
	}
	
}
