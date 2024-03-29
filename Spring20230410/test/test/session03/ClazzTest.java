package test.session03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.session03.bean.Clazz;
import com.spring.core.session03.bean.Student;
import com.spring.core.session03.bean.Teacher;

public class ClazzTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3.xml");
		Clazz clazz1 = ctx.getBean("clazz1", Clazz.class);
		System.out.println(clazz1);
		Clazz clazz2 = ctx.getBean("clazz2", Clazz.class);
		System.out.println(clazz2);
		Clazz clazz3 = ctx.getBean("clazz3", Clazz.class);
		System.out.println(clazz3);
		
		Student student1 = ctx.getBean("student1", Student.class);
		Student student2 = ctx.getBean("student2", Student.class);
		System.out.println(student1);
		System.out.println(student2);
		
		Teacher teacher1 = ctx.getBean("teacher1", Teacher.class);
		System.out.println(teacher1);
		
		Teacher teacher2 = ctx.getBean("teacher2", Teacher.class);
		System.out.println(teacher2);
		
		// 1. teacher1 與 teacher2 各教授幾學分 ?
		int sumOfCredit1 = teacher1.getClazzs().stream().mapToInt(Clazz::getCredit).sum();
		int sumOfCredit2 = teacher2.getClazzs().stream().mapToInt(Clazz::getCredit).sum();
		System.out.println(sumOfCredit1 + ", " + sumOfCredit2);
		// 2. teacher1 與 teacher2 薪資各是多少 ?
		//    薪資 = base + (總教授學分數*addition)
		int totalOfSalary1 = teacher1.getSalary().get("base") + teacher1.getSalary().get("addition") * sumOfCredit1;
		int totalOfSalary2 = teacher2.getSalary().get("base") + teacher2.getSalary().get("addition") * sumOfCredit2;
		System.out.println(totalOfSalary1 + ", " + totalOfSalary2);
	}

}