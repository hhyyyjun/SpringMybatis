package com.kim.biz.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//에러가 발생했을 때 횡단관심처럼 동작하게 만들어주는 어노테이션
//@ControllerAdvice("com.kim.biz") //CommonExceptionHandler를 객체화해주는 어노테이션
public class CommonExceptionHandler {
	//2022.09.28 에러페이지 작성
	
	@ExceptionHandler(NullPointerException.class) //어떤 종류의 예외인지
	public ModelAndView aException(Exception e) { 
		//어떤 예외가 발생했는지 보내기 위해 mav로 리턴
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception",e);
		mav.setViewName("/error/error.jsp");
		return mav;
	}
	@ExceptionHandler(ArithmeticException.class) 
	public ModelAndView bException(Exception e) { 
		//어떤 예외가 발생했는지 보내기 위해 mav로 리턴
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception",e);
		mav.setViewName("/error/error.jsp");
		return mav;
	}
	@ExceptionHandler(Exception.class)
	public ModelAndView cException(Exception e) { //미확인
		//어떤 예외가 발생했는지 보내기 위해 mav로 리턴
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception",e);
		mav.setViewName("/error/error.jsp");
		return mav;
	}
}
