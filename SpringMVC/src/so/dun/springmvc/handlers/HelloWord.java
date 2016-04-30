package so.dun.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWord {
	
	private static final String SUCCESS = "success";
	
	public HelloWord() {
		System.out.println("HelloWord Constructor");
	}
	
	/**
	 * 使用@RequestMapping注解来映射请求
	 * 返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver视图解析器，会做如下的解析
	 * 通过prefix + returnVal + suffix这样的方式，得到实际的物理视图，然后转发
	 * @return
	 */
	@RequestMapping("/helloword")
	public String hello(){
		System.out.println("Hello Word");
		return SUCCESS;
	}

}
