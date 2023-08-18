package cn.hwyee.common;

import cn.hwyee.common.config.properties.SnowflakeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ProjectYH
 */
@SpringBootApplication
public class CommonApplication implements CommandLineRunner {
    @Autowired
    private SnowflakeProperties snowflakeProperties;
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(snowflakeProperties);
    }
}
