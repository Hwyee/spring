package cn.hwyee.common.config;

import cn.hwyee.common.pojo.MovieCatalog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName PrimaryAnnotationTestConfig
 * @description 测试@primary注解
 * @date 2023/4/12
 * @since JDK 1.8
 */
@Configuration
public class PrimaryAnnotationTestConfig {

    @Bean({"ii","iq"})
    public MovieCatalog firstMovieCatalog() {
        return null;
    }

    @Bean("ii")
    @Primary
    public MovieCatalog secondMovieCatalog() {
        return new MovieCatalog();
    }
}
