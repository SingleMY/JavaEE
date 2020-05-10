package spring.mvc.daobean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.mvc.dao.*;



/**
 *
 * @author QinKuai
 * 创建时间：2020年4月6日
 * 描述：DAO层Bean配置
 * 		等价于之前的XML配置模式
 */
@Configuration
public class DaoBean {
    @Bean
    public StudentDao getStudentDao() {
        return StudentDao.getInstance();
    }

    @Bean
    public HomeworkDao getHomeworkDao() {
        return HomeworkDao.getInstance();
    }

    @Bean
    public TeacherDao geTeacherDao() {
        return TeacherDao.getInstance();
    }

    @Bean
    public SubmitDao getSubmitDao() {
        return SubmitDao.getInstance();
    }
    @Bean
    public TeachingDao getTeachingDao() {
        return TeachingDao.getInstance();
    }
}
