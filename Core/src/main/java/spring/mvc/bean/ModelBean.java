package spring.mvc.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import spring.mvc.model.*;

@Configuration
@Component
@Scope("prototype")
public class ModelBean {

  @Bean
    public static Student getStudent(){
      return new Student();
  }

  @Bean
    public static Homework getHomework(){
      return new Homework();
  }

  @Bean
    public static Teacher getTeacher(){
      return new Teacher();
  }

  @Bean
    public static Submit getSubmit(){
      return new Submit();
  }

  @Bean
    public static sHomework getsHomework(){
      return  new sHomework();
  }
}
