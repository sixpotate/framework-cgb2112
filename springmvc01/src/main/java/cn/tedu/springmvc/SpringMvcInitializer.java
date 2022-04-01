package cn.tedu.springmvc;

import cn.tedu.springmvc.config.SpringConfig;
import cn.tedu.springmvc.config.SpringMvcConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring MVC项目的初始化类
 */
public class SpringMvcInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // 返回自行配置的Spring相关内容的类
        return new Class[] { SpringConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // 返回自行配置的Spring MVC相关内容的类
        return new Class[] { SpringMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // 返回哪些路径是由Spring MVC框架处理的
        return new String[] { "*.do" };
    }

}
