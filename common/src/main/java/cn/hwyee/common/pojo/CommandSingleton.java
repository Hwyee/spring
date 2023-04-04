package cn.hwyee.common.pojo;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author hui
 * @version 1.0
 * @className CommandSingleton
 * @description
 * @date 2023/4/4
 * @since JDK 1.8
 */
@Component
@Scope(value = "singleton",proxyMode = ScopedProxyMode.NO)
public class CommandSingleton {

}
