package cn.hwyee.common;

import cn.hwyee.common.service.LookupAnnotationService;
import cn.hwyee.common.service.impl.LookupAnnotationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class CommonApplicationTests {
//    @Resource
//    private LookupAnnotationServiceImpl lookupAnnotationService;
    @Test
    void lookup() {

        AnnotationConfigApplicationContext applicationContext1 =
                new AnnotationConfigApplicationContext(CommonApplication.class);
        AnnotationConfigApplicationContext applicationContext2 =
                new AnnotationConfigApplicationContext(CommonApplication.class);
        Object lookupAnnotationService =
                 applicationContext1.getBean("lookupAnnotationService");
        Object lookupAnnotationService1 =
                 applicationContext1.getBean("scopedTarget.lookupAnnotationService");
        System.out.println(lookupAnnotationService.toString());
        System.out.println(lookupAnnotationService.getClass());
        System.out.println(lookupAnnotationService1);
        System.out.println(lookupAnnotationService1.getClass());
        LookupAnnotationService lookupAnnotationService0 =
                applicationContext1.getBean("lookupAnnotationService", LookupAnnotationService.class);
        System.out.println(lookupAnnotationService0);
        LookupAnnotationServiceImpl lookupAnnotationServiceImpl1 =
                applicationContext1.getBean("lookupAnnotationService", LookupAnnotationServiceImpl.class);
        LookupAnnotationServiceImpl lookupAnnotationServiceImpl11 =
                applicationContext1.getBean("lookupAnnotationService", LookupAnnotationServiceImpl.class);
        LookupAnnotationServiceImpl lookupAnnotationServiceImpl2 =
                applicationContext2.getBean("lookupAnnotationService", LookupAnnotationServiceImpl.class);
        System.out.println(lookupAnnotationServiceImpl1);
        System.out.println(lookupAnnotationServiceImpl11);
        System.out.println(lookupAnnotationServiceImpl2);






//        for (int i = 0; i < 10; i++) {
//            System.out.println(this.lookupAnnotationService.createCommandPrototype());
//        }
    }

}
