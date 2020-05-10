package spring.mvc.aspect;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

public class DatabaseConnectionAspect {
    @Pointcut("execution(**com.qinkuai.webservice.service.CourseController.*(..))")
    public void homework() {}

    @Before("homework()")
    public void load() {
        System.out.println("Method In HomeworkController.class Start!");
    }

    @AfterReturning("homework()")
    public void end() {
        System.out.println("Method In HomeworkController.class End!");
    }
}
