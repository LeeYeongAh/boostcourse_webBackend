package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class ApplicationContextExam4 {
    public static void main(String[] args){
      ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig2.class);
      
      //Car car = (Car) ac.getBean("car");
      //Car car = (Car) ac.getBean(Car.class);
      Car car = ac.getBean(Car.class);
      car.run();
      
      
    }
}
