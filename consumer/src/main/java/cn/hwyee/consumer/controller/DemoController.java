package cn.hwyee.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hui
 * @version 1.0
 * @className DemoController
 * @description
 * @date 2023/6/9
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
public class DemoController {

    @RequestMapping("/success")
    public String success(){
        return "success";
    }
}
