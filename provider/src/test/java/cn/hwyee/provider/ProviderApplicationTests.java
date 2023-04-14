package cn.hwyee.provider;

import cn.hwyee.provider.service.impl.AsyncThreadService;
import cn.hwyee.provider.service.impl.RabbitmqProviderService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
class ProviderApplicationTests {
    @Resource
    private RabbitmqProviderService rabbitmqProviderService;
    @Resource
    private AsyncThreadService asyncThreadService;
    @Test
    void rabbitTest1() {
        for (int i = 0; i < 10; i++) {
            rabbitmqProviderService.test("test","生产者信息..." + i);
        }
    }

    @Test
    void asyncTest1() {
        List<String> list = new ArrayList<>();
        for (int i = 10; i > 0; i--) {
            String s = asyncThreadService.asyncDirectReturnString("test" + i, i * 100L);
            list.add(s);
        }
        System.out.println(list);
    }

    @Test
    void asyncTest2() {
        List<CompletableFuture<String>> list = new ArrayList<>();
        for (int i = 10; i > 0; i--) {
            CompletableFuture<String> s = asyncThreadService.asyncReturnFutureString("test" + i, i * 100L);
            list.add(s);
        }
        list.forEach(t -> {
            try {
                System.out.println(t.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
