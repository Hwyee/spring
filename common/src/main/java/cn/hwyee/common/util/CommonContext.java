package cn.hwyee.common.util;

import jakarta.annotation.Resource;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author hui
 * @version 1.0
 * @className CommonContext
 * @description
 * @date 2023/4/3
 * @since JDK 1.8
 */
public class CommonContext {
    @Resource
    private GenericApplicationContext genericApplicationContext;
    public void regBean(){
        genericApplicationContext.getDefaultListableBeanFactory().
                registerSingleton("commonContext",new CommonContext());
    }
}
