package cn.hwyee.common.util;

import cn.hutool.Hutool;
import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author hui
 * @version 1.0
 * @className JudgeUtil
 * @description 判断
 * @date 2023/5/18
 * @since JDK 1.8
 */
@Slf4j
public class JudgeUtil {
    private JudgeUtil(){}

    public static void main(String[] args) {
        String[] treatTypeArr = new String[] { "17", "18" };
        log.info(String.valueOf(ArrayUtil.contains(treatTypeArr, "11")));
        log.info(String.valueOf(Arrays.asList(treatTypeArr).contains("11")));
        log.info(String.valueOf(ArrayUtil.contains(treatTypeArr, "11")));
    }
}
