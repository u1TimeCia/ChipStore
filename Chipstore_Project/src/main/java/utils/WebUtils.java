package utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    /**
     * User BeanUtils tool to inject parameters to object
     * req.getParameterMap(): use setXXX in class to set the parameter and inject to object
     * @param value
	 * @param bean
     * @return
     * @author Haoran Qi
     * @date
     */
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Integer parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
