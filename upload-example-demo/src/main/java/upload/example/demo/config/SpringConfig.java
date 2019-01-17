package upload.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    //配置applicationContext,用于自动注入bean
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringConfig.applicationContext == null){
            SpringConfig.applicationContext = applicationContext;
        }
    }

}
