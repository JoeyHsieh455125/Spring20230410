package test.session07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session07.tx.controller.BookController;

public class BuyManyBookTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		BookController controller = ctx.getBean(BookController.class);
		controller.buyBooks("John", 1, 1, 1);
	}
}
