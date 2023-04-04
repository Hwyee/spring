package cn.hwyee.common.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author hui
 * @version 1.0
 * @className CommandPrototype
 * @description
 * @date 2023/4/4
 * @since JDK 1.8
 */
@Component
@Scope(value = "prototype")
public class CommandPrototype {
}
