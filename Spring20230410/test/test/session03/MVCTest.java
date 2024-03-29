package test.session03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session03.mvc.controller.UserController;
import com.spring.core.session03.mvc.model.User;

public class MVCTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3-mvc.xml");
		User user = ctx.getBean("user", User.class);
//		User user = ctx.getBean("member", User.class);
		System.out.println(user);
		
		// 將 user 藉由 MVC 模擬加入資料庫
		// UserController -> UserService -> UserDao
		UserController userController = ctx.getBean("userController", UserController.class);
		userController.appendUser(user);
	}
}
