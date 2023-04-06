package cn.hwyee.provider;

import cn.hwyee.provider.service.impl.RabbitmqProviderService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProviderApplicationTests {
    @Resource
    private RabbitmqProviderService rabbitmqProviderService;

    @Test
    void rabbitTest1() {
        for (int i = 0; i < 10; i++) {
            rabbitmqProviderService.test("test","生产者信息..." + i);
        }
    }

}
