package spring.mvc.session08.controller;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session08.entity.User;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {
	
	// 1. 取得字串資料
	// 設計可以到執行到此方法的路徑
	// 路徑：/mvc/hello/welcome
	@RequestMapping(value = "/welcome")
	@ResponseBody
	public String welcome() {
		return "Welcome Spring MVC";
	}
	
	/*
	 * 2. ? 帶參數 @RequestParm
	 * 路徑：/mvc/hello/sayhi?name=John&age=18
	 */
	@RequestMapping(value = "sayhi")
	@ResponseBody
	public String sayHi(@RequestParam(value = "name") String name,
						@RequestParam(value = "age") Integer age) {
		String data = String.format("Hi %s %d", name, age);
		return data;
	}
	
	/*
	 * 3. Lab 練習
	 * 路徑：/mvc/hello/bmi?h=170.0&w=60.0
	 * 請設計一個 bmi 方法執行上述路徑後會得到 bmi = 20.76
	 */
	@RequestMapping(value = "/bmi")
	@ResponseBody
	public String bmi(@RequestParam(value = "h") String h,
					  @RequestParam(value = "w") String w) {
		double hight = Double.parseDouble(h);
		double weight = Double.parseDouble(w);
		
		double bmi = weight / Math.pow((hight/100), 2);
		
		return String.format("bmi = %.2f", bmi);
	}
	
	/*
	 * 4. 多筆參數
	 * 路徑：/mvc/hello/age?age=18&age=19&age=24
	 * 計算平均年齡
	 */
	@RequestMapping(value = "/age")
	@ResponseBody
	public String getAvgOfAge(@RequestParam("age") List<Integer> ageList) {
		double avg = ageList.stream().mapToInt(Integer::intValue).average().getAsDouble();
		
		return String.format("avg = %.1f", avg);
	}
	
	/*
	 * 5. Lab 練習：得到多筆 score 資料
	 * 路徑：/mvc/hello/exam?score=80&score=100&score=50
	 * 求出最高分、最低分、平均與總分
	 */
	@RequestMapping(value = "/exam", produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
	@ResponseBody
	public String getResult(@RequestParam(value = "score") List<Integer> scores) {
//		int max = scores.stream().mapToInt(score -> score.intValue()).max().getAsInt();
//		int min = scores.stream().mapToInt(score -> score.intValue()).min().getAsInt();
//		double avg = scores.stream().mapToInt(Integer::intValue).average().getAsDouble();
//		int sum = scores.stream().mapToInt(Integer::intValue).sum();
		
		IntSummaryStatistics stat = scores.stream()
				  .mapToInt(Integer::intValue)
				  .summaryStatistics();

		
//		return String.format("最高分: %d, 最低分: %d, 平均: %.2f, 總分: %d", max, min, avg, sum);
		return String.format("最高分: %d, 最低分: %d, 平均: %.2f, 總分: %d", stat.getMax(), stat.getMin(), stat.getAverage(), stat.getSum());

	}
	
	/*
	 * 6. Lab 練習：得到多筆資料轉 Map
	 * 路徑：/mvc/hello/person?name=John&score=90&age=18&pass=true
	 * 路徑：/mvc/hello/person?name=Mary&score=40&age=17&pass=true
	 */
	@RequestMapping(value = "/person")
	@ResponseBody
	public String getPerson(@RequestParam Map<String, String> person) {
		return person.toString();
	}
	
	/*
	 * 7. Lab 練習：參數自動匹配物件
	 * 路徑：/mvc/hello/user?name=John&score=90&age=18&pass=true
	 * 路徑：/mvc/hello/user?name=Mary&score=40&age=17&pass=true
	 */
	@RequestMapping(value = "/user")
	@ResponseBody
	public String getUser(User user) {
		return user.toString();
	}
	
	/*
	 * 8. Lab 路徑參數 @PathVariable
	 * 路徑：/mvc/hello/javaexam/75
	 * 路徑：/mvc/hello/javaexam/45
	 */
	@RequestMapping(value = "/javaexam/{score}")
	@ResponseBody
	public String getJavaexam(@PathVariable("score") Integer score) {
		return String.format("%d %s", score, (score >= 60)?"pass" :"fail");
	}
	
	/*
	 * 9. Lab 練習：@PathVariable + @RequestParam 混用練習
	 * 路徑：/mvc/hello/calc/add?x=30&y=20 	-> Result : 50
	 * 路徑：/mvc/hello/calc/sub?x=30&y=20 	-> Result : 10
	 * 路徑：/mvc/hello/calc/sub?y=20 		-> Result : -20
	 * 路徑：/mvc/hello/calc/add			 	-> Result : 0
	 * 路徑：/mvc/hello/calc/sub			 	-> Result : 0
	 */
	@RequestMapping(value = "/calc/{exp}")
	@ResponseBody
	public String calcExp(@PathVariable("exp") String exp,
						  @RequestParam(value = "x", required = false, defaultValue = "0") Integer x,
						  @RequestParam(value = "y", required = false, defaultValue = "0") Integer y) {
		int result = 0;
		
		if(exp.equals("add")) {
			result = x + y;
		}else if(exp.equals("sub")) {
			result = x - y;
		}else {
			return "exp value error";
		}
		
//		switch(exp) {
//			case "add":
//				result = x + y;
//				break;
//			case "sub":
//				result = x - y;
//				break;
//			default:
//				return "exp value error";
//		}
		return String.format("Result = %d", result);
	}
	
	/*
	 * 10. @PathVariable (萬用字元：任意多字(*)，任意一字(?))
	 * 路徑：/mvc/hello/any/aabbcc/java8
	 * 路徑：/mvc/hello/any/a/java7
	 */
	@RequestMapping(value = "/any/*/java?")
	@ResponseBody
	public String any() {
		return "Any";
	}
	
	/*
	 * 11. @PathVariable (規則表示式)
	 * 路徑：/mvc/hello/student/vincent-18
	 * 路徑：/mvc/hello/student/anita-19
	 */
	@RequestMapping(value = "/student/{name:[a-z]+}-{age:\\d+}")
	@ResponseBody
	public String getName(@PathVariable("name") String name, @PathVariable("age") String age) {
		return name + " " + age;
	}
}
