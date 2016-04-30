package so.dun.springmvc.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import so.dun.springmvc.dao.EmployeeDao;
import so.dun.springmvc.entities.Employee;
import so.dun.springmvc.entities.User;
import so.dun.springmvc.exception.UserNameNotMatchPasswordException;

//@SessionAttributes(value={"user"},types={String.class})
@RequestMapping("/springmvc")
@Controller
public class Test {
	
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	
	/**
	 * @RequsetMapping修饰类和方法
	 */
	@RequestMapping("/testmapping")
	public String testMapping(){
		System.out.println("testMapping");
		return SUCCESS;
	}

	/**
	 * 使用Method属性来指定请求方式。
	 */
	@RequestMapping(value="/testMethod",method=RequestMethod.POST )
	public String testMethod(){
		System.out.println("testMethod");
		return SUCCESS;
	}
	
	/**
	 * 可以使用params和headers来更加精确的映射请求。params和headers支持简单的表达式
	 */
	@RequestMapping(value="/testparamsandheaders",params={"username","age!=18"} )
	public String testParamsAndHeaders(){
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	@RequestMapping("/testantpath/*/abc")
	public String testAntPath(){
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	/**
	 * @Pathvariable 可以映射URL中的占位符到目标方法中的参数中
	 */
	@RequestMapping(value="/testpathvariable/{id}" )
	public String testPathVariable(@PathVariable(value="id") Integer Id){
		System.out.println("testPathVariable"+Id);
		return SUCCESS;
	}
	
	
	/**
	 * REST风格的URL，以CRUD为例
	 * 增：/order POST
	 * 删：/order/1 DELETE   -->delete?id=1
	 * 改：/order/1 PUT		-->update?id=1
	 * 查：/order/1 GET		-->get?id=1
	 * 如何发送PUT请求和DELETE请求呢？
	 * 1、需要配置HiddenHttpMethodFilter
	 * 2、需要发送POST请求
	 * 3、需要在发送POST请求时，携带一个name="_method"的隐藏域，值为DELETE或者PUT
	 * 在SpringMVC目标方法中如何得到id，使用@PathVariable注解
	 */
	@RequestMapping(value="/testrest/{id}",method=RequestMethod.GET)
	public String testRestGet(@PathVariable(value="id") Integer ID){
		System.out.println("test Get  " + ID);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testrest/",method=RequestMethod.POST)
	public String testRestPost(){
		System.out.println("test Post");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testrest/{id}",method=RequestMethod.DELETE)
	public String testRestDelete(@PathVariable(value="id") Integer ID){
		System.out.println("test Delete " + ID);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testrest/{id}",method=RequestMethod.PUT)
	public String testRestPut(@PathVariable(value="id") Integer ID){
		System.out.println("test Put " +ID);
		return SUCCESS;
	}
	
	/**
	 * @RequestParam 来映射请求参数
	 * value 值为请参数的参数名
	 * required 该参数是否必须，默认值为true
	 * defaultValue 请求参数的默认值
	 */
	@RequestMapping(value="/testrequestparam")
	public String testRequestParam(@RequestParam(value="username",required=false) String un,
			@RequestParam(value="age",required=false,defaultValue="0") int age){
		System.out.println("test RequestParam " +un+" "+age);
		return SUCCESS;
	}
	
	/**
	 * @CookieValue：映射一个Cookie值，属性包括value，required，defaultValue
	 */
	@RequestMapping(value="/testcookievalue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sessionId){
		System.out.println("test CookieValue " + sessionId);
		return SUCCESS;
	}
	
	/**
	 * Spring MVC 会按照请求参数名和POJO属性名进行自动匹配，自动为该对象填充属性，直吹级联属性。如user.city
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user){
		System.out.println("test Pojo "+user);
		return SUCCESS;
	}
	/**
	 * 可以试用Servlet原生的API作为目标方法参数。
	 * {@link HttpServletRequest}
	 * {@link HttpServletResponse}
	 * {@link HttpSession}
	 * {@link Principal}
	 * {@link Local}
	 * {@link InputStream}
	 * {@link OutputStream}
	 * {@link Reader}
	 * {@link Writer} 
	 */
	@RequestMapping("/testservletapi")
	public String testServletAPI(HttpServletRequest request,HttpServletResponse reponse){
		System.out.println("test ServletAPI " +request +" "+ reponse);
		return SUCCESS;
	}
	/**
	 * 目标方法的返回值可以是ModelAndView类型。
	 * 其中可以包含视图和模型信息。
	 * SpringMVC会把ModelAndView的model数据放入到request域对象中
	 */
	@RequestMapping("/testmodelandview")
	public ModelAndView testModelAndView(){
		String viewName=SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewName);
		//添加模型数据到ModelAndView中
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}
	
	/**
	 * 目标方法可以添加Map类型（实际上也可以是Model类型或ModelMap类型）的参数
	 */    
	@RequestMapping("/testmap")
	public String testMap(Map<String,Object> map){
		map.put("names", Arrays.asList("Tom","Tony","Sheey"));
		return SUCCESS;
	}
	/**
	 * @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外（实际上使用的是value属性值），
	 * 还可以通过模型属性的对象类型指定那些模型属性需要放到会话中（实际上使用的是type属性值）
	 * 这个注解只能放在类上面，不能放在方法上面
	 */
	@RequestMapping("/testsession")
	public String testSessionAttributes(Map<String,Object> map){
		User user = new User("dunso", "123", "admin@dun.so", 19);
		map.put("user", user);
		map.put("username", "dunso");
		return SUCCESS;
	}
	
	/**
	 * 运行流程：
	 * 1.执行@ModelAttribute 注解修饰的方法：从数据库中取出对象，把对象放入到了Map中，键为：user
	 * 2.SpringMVC从Map中取出User对象，并把表单的请求参数赋给该User对象对应的属性
	 * 3.SpringMVC把上述对象传入目标方法的参数中
	 * 注：在@ModelAttribute修饰的方法中，放入到Map是的键需要和目标方法入参类型一致（只不过放入map中的键第一个字母小写）
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(@ModelAttribute("user") User user){
		System.out.println("修改 "+user);
		return SUCCESS;
	}
	
	/**
	 * 1、每个@ModelAttribute 标记的方法，会在每个目标方法执行之前被SpringMVC调用。
	 * 2、@ModelAttribute注解也可以修饰目标方法POJO类型的入参，其value属性值有如下作用：
	 * 》SpringMVC会使用value属性值在implicitModel中查找对应的对象，若存在则会直接传入到目标方法的入参中
	 * 》SpringMVC 会以value为键，POJO类型的对象为值，存入到Request中
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id",required=false) Integer id,
			Map<String,Object> map){
		if(id != null){
			//模拟从数据库中获取对象
			User user = new User(1, "dunso", "123", "admin@dun.so", 19);
			System.out.println("从数据库中获取一个对象");
			map.put("user", user);
		}
	}	
	
	@RequestMapping("/testViewAndViewResolver")
	public String testViewAndViewResolver(){
		System.out.println("test ViewAndViewResolver");
		return SUCCESS;
	}
	
	@RequestMapping("testView")
	public String testView(){
		System.out.println("test View");
		return "helloView";
	}
	
	@RequestMapping("/testRedirct")
	public String testRedirct(){
		System.out.println("test Redirect");
		return "redirect:/index.jsp";
	}
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@RequestMapping("/testConversionServiceConverer")
	public String testConvertor(@RequestParam("employee") Employee employee){
		System.out.println("save employee "+employee );
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson(){
		return employeeDao.getAll();
	}
	
	
	/**
	 * 文件上传
	 */
	@ResponseBody
	@RequestMapping("/testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body){
		System.out.println(body);
		
		return "htlloworld! "+ new Date() ;
	}
	
	@RequestMapping("/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
		byte[] body = null;
		ServletContext servletContext =session.getServletContext();
		InputStream in = servletContext.getResourceAsStream("files/dunso.txt");
		body = new byte[in.available()];
		in.read(body);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=dunso.txt");
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("/i18n")
	public String testI18n(Locale locale){
		System.out.println("i18n+++");
		String val = messageSource.getMessage("i18n.username", null, locale);
		System.out.println(val);
		return SUCCESS;
	}
	
	@RequestMapping("\testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc,
			@RequestParam("file") MultipartFile file) throws IOException{
		System.out.println("desc "+desc);
		System.out.println("OriginalFilename: " + file.getOriginalFilename());
		System.out.println("InputStream: "+ file.getInputStream());
		return SUCCESS;
	}
	
	@RequestMapping("/testExceptionHandlerExceptionResolver")
	public String testExceptionHandlerExceptionResolver(@RequestParam("i") int i){
		System.out.println("result: " + (10 / i));
		return SUCCESS;
	}
	
	/**
	 * 1、在@ExceptionHandler 方法的入参中可以加入Exception类型的参数，该参数即对应发生的异常对象
	 * 2、@ExceptionHandler 方法的入参中不能传入Map.若希望把宜昌信息传到页面上，需要使用ModelAndView作为返回值
	 * 3、@ExceptionHandler 方法标记的异常有优先级的问题
	 * 4、@ControllerAdvice: 如果在当前Handler中找不到@ExceptionHandler方法来处理当前的异常，
	 *    则将去@ControllerAdvice标记的类中查找@ExceptionHandler标记的方法来处理异常
	 */
	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView handleArithmeticException(Exception ex){
		ModelAndView mv = new ModelAndView(ERROR);
		mv.addObject("exception", ex);
		System.out.println("0因子异常： " + ex);
		return mv;
	}
	
	@RequestMapping("testResponseStatusExceptionResolver")
	public String testResponseStatusExceptionResolver(@RequestParam("i") int i){
		if(i == 13){
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("testResponseStatusExceptionResolver");
		return SUCCESS;
	}
	@RequestMapping(value="/testDefaultHandlerExceptionResolver",method = RequestMethod.POST)
	public String testDefaultHandlerExceptionResolver(){
		System.out.println("testDefaultHandlerExceptionResolver");
		return SUCCESS;
	}
	
	@RequestMapping("/testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(@RequestParam("i") int i){
		String [] vals = new String[10];
		System.out.println(vals[i]);
		return SUCCESS;
	}
}












