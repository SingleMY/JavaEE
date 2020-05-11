package spring.mvc.aspect;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

public class DatabaseConnectionAspect {
    @Pointcut("execution(**spring.mvc.util.JdbcUtil.*(..))")
    public void createConnection() {}

    @Before("createConnection()")
    public void load() {
        System.out.println("Method createConnection Start!");
    }

    @AfterReturning("createConnection()")
    public void end() {
        System.out.println("Method createConnection End!");
    }
}
