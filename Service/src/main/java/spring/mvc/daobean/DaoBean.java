package spring.mvc.daobean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import spring.mvc.dao.*;

@Configuration
@Scope("prototype")
public class DaoBean {
    @Bean
    public static StudentDao getStudentDao() {
        return StudentDao.getInstance();
    }

    @Bean
    public static HomeworkDao getHomeworkDao() {
        return HomeworkDao.getInstance();
    }

    @Bean
    public static TeacherDao geTeacherDao() {
        return TeacherDao.getInstance();
    }

    @Bean
    public static SubmitDao getSubmitDao() {
        return SubmitDao.getInstance();
    }
    @Bean
    public static TeachingDao getTeachingDao() {
        return TeachingDao.getInstance();
    }
}
