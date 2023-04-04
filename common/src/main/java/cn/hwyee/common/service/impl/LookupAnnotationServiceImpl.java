package cn.hwyee.common.service.impl;

import cn.hwyee.common.pojo.CommandPrototype;
import cn.hwyee.common.service.LookupAnnotationService;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author hui
 * @version 1.0
 * @className LookupAnnotationServiceImpl
 * @description
 * @date 2023/4/4
 * @since JDK 1.8
 */
@Component
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class LookupAnnotationServiceImpl  implements LookupAnnotationService{
//    @Lookup
//    public CommandPrototype createCommandPrototype() {
//        return null;
//    }

    @Override
    public String test() {
        return null;
    }
}
