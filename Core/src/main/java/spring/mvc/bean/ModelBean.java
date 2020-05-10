package spring.mvc.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import spring.mvc.model.*;

@Configuration
@Scope("prototype")
public class ModelBean {

  @Bean
    public Student getStudent(){
      return new Student();
  }

  @Bean
    public Homework getHomework(){
      return new Homework();
  }

  @Bean
    public Teacher getTeacher(){
      return new Teacher();
  }

  @Bean
    public Submit getSubmit(){
      return new Submit();
  }

  @Bean
    public sHomework getsHomework(){
      return  new sHomework();
  }
}
