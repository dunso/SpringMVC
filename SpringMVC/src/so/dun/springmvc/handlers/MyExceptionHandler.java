package so.dun.springmvc.handlers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler({RuntimeException.class})
	public ModelAndView handleArithmeticException2(Exception ex){
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex);
		System.out.println("RunTime异常： " + ex);
		return mv;
	}

}
