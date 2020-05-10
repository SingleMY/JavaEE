package spring.mvc.util;
/**
 * Demo class
 *
 * @author keriezhang
 * @date 2016/10/31
 */
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

// 工具类
public class FieldUtils {
    private FieldUtils() {}

    // 通过传入特定类获取类的属性列表
    // 使其与数据库的数据类型对应
    public static void getClassFields(List<Class<?>> list, Class<?> targetClazz) {
        for (Field field : targetClazz.getDeclaredFields()) {
            if (field.getType() == Date.class) {
                list.add(java.sql.Timestamp.class);
            }else {
                list.add(field.getType());
            }
        }
    }
}
