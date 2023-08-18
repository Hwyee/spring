package cn.hwyee.common.util;

import java.math.BigInteger;

import static cn.hwyee.common.util.CalcEnum.EIGHT;
import static cn.hwyee.common.util.CalcEnum.FIVE;
import static cn.hwyee.common.util.CalcEnum.FOUR;
import static cn.hwyee.common.util.CalcEnum.SEVEN;
import static cn.hwyee.common.util.CalcEnum.SIX;
import static cn.hwyee.common.util.CalcEnum.THREE;
import static cn.hwyee.common.util.CalcEnum.TWO;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Test
 * @description
 * 阿里数字马力面试
 * 问题1：
 * 实现个税计算
 * //1-5000 税率0
 * 5001-8000 3%
 * 8001-17000 10%
 * 17001-30000 20%
 * 30001-40000 25%
 * 40001-60000 30%
 * 60001-85000 35%
 * 85000- 45%
 * 要求
 * //1.逻辑正确，代码优雅
 * 2.可扩展性，考虑区间的变化，比如起征点从5000变成10000等等，或者说85000以上的征税50%
 * 这里举个例子，比如税前10000元，5000部分是不扣税，后面5000，3000扣税3%，2000%扣税10%
 * 3.可以用IDE，但是不能上网查资料
 * 4.笔试时间45分钟，代码贴到下面【这是必须的，网站到点会自动结束面试，要注意】
 * 5.笔试通过的话，会再打一次电话
 * @date 2023/8/18
 * @since JDK 1.8
 */
public class InterviewAli {

    public static void main(String[] args) {
        InterviewAli test = new InterviewAli();
        System.out.println(test.calc(8100));
    }

    /**
     * calc:
     * 计算个税
     * @author hui
     * @version 1.0
     * @param amount
     * @return double
     * @date 2023/8/18 19:54
     */
    public double calc(double amount) {
        double result = 0;
        CalcEnum calcEnum = judgeCalc(amount);
        switch (calcEnum) {
            case EIGHT:
                result += (amount - EIGHT.value) * EIGHT.calc;
            case SEVEN:
                if (result != 0) {
                    result += 8750;
                } else {
                    result += (amount - SEVEN.value) * SEVEN.calc;
                }
            case SIX:
                if (result != 0) {
                    result += 6000;
                } else {
                    result += (amount - SIX.value) * SIX.calc;
                }
            case FIVE:
                if (result != 0) {
                    result += 2500;
                } else {
                    result += (amount - FIVE.value) * FIVE.calc;
                }
            case FOUR:
                if (result != 0) {
                    result += 2600;
                } else {
                    result += (amount - FOUR.value) * FOUR.calc;
                }
            case THREE:
                if (result != 0) {
                    result += 900;
                } else {
                    result += (amount - THREE.value) * THREE.calc;
                }
            case TWO:
                if (result != 0) {
                    result += 90;
                } else {
                    result += (amount - TWO.value) * TWO.calc;
                }
            case ONE:
                result += 0;
                break;
        }
        return result;
    }


    /**
     * judgeCalc:
     * 判断当前金额是个税的哪个等级
     * @author hui
     * @version 1.0
     * @param amount
     * @return cn.hwyee.common.util.CalcEnum
     * @date 2023/8/18 19:53
     */
    public CalcEnum judgeCalc(double amount) {
        if (amount > EIGHT.value) {
            return EIGHT;
        } else if (amount > CalcEnum.SEVEN.value) {
            return CalcEnum.SEVEN;
        } else if (amount > SIX.value) {
            return SIX;
        } else if (amount > FIVE.value) {
            return FIVE;
        } else if (amount > FOUR.value) {
            return FOUR;
        } else if (amount > THREE.value) {
            return THREE;
        } else if (amount > TWO.value) {
            return TWO;
        } else {
            return CalcEnum.ONE;
        }
    }


}

/**
 * 个税等级枚举:
 *
 * @author hui
 * @version 1.0
 * @return
 * @date 2023/8/18 19:53
 */
enum CalcEnum {
    ONE(0, 0),
    TWO(5000, 0.03),
    THREE(8000, 0.10),
    FOUR(17000, 0.20),
    FIVE(30000, 0.25),
    SIX(40000, 0.30),
    SEVEN(60000, 0.35),
    EIGHT(85000, 0.45);
    public final double value;
    public final double calc;

    CalcEnum(double value, double calc) {
        this.value = value;
        this.calc = calc;
    }

    public double getValue() {
        return this.value;
    }

    public double getCalc() {
        return this.calc;
    }
}
