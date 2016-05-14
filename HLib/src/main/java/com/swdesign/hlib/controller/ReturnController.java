package com.swdesign.hlib.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swdesign.hlib.service.*;

@Controller
public class ReturnController {
	
	IReturn iReturn;

	
	//? post..?
	@RequestMapping("/return")
	public String Return(HttpServletRequest request, Model model){
		
		// 실행 log
		System.out.println("return()");
		iReturn = new ReturnManager();
		// model에 인자 넣어줌
		model.addAttribute("Request", request);
		
		iReturn.execute(model);
		
		//return에 해당하는 jsp화면으로 넘긴다...
		return "returnCheck";
	}
	
	@RequestMapping("/returnCheck")
	public String ReturnCheck(HttpServletRequest request, Model model) {
		System.out.println("returnCheck()");
		iReturn = new ReturnCheck();
		
		model.addAttribute("Request", request);
		
		iReturn.execute(model);
		
		return "redirect:return";
		
	}
}
