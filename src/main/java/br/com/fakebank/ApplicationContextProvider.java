package br.com.fakebank;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ApplicationContextProvider.CONTEXT = context;
    }

    public static<T> T getBean(Class<T> tipo) {
        return ApplicationContextProvider.CONTEXT.getBean(tipo);
    }
    
    public static<T> T getBean(String qualifier, Class<T> tipo) {
        return ApplicationContextProvider.CONTEXT.getBean(qualifier , tipo);
    }   
}