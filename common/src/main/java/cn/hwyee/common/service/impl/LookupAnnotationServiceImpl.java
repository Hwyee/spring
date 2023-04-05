package cn.hwyee.common.service.impl;

import cn.hwyee.common.pojo.CommandPrototype;
import cn.hwyee.common.service.LookupAnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * @author hui
 * @version 1.0
 * @className LookupAnnotationServiceImpl
 * @description
 * @date 2023/4/4
 * @since JDK 1.8
 */
@Component("lookupAnnotationService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LookupAnnotationServiceImpl implements LookupAnnotationService{
//    @Autowired
//    public CommandPrototype commandPrototype;
//    @Lookup
//    public CommandPrototype createCommandPrototype() {
//        return null;
//    }
public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(LookupAnnotationServiceImpl.class);
    context.refresh();
    String[] beanDefinitionNames = context.getBeanDefinitionNames();
    Stream.of(beanDefinitionNames).forEach(t -> {
        Class<?> type = context.getType(t);
        System.out.printf("beanName : %s ---> beanType %s \n",t,type.getName());
        System.out.println(context.getBean(t));
    });

}
    @Override
    public CommandPrototype test() {
        return new CommandPrototype();
    }
}
