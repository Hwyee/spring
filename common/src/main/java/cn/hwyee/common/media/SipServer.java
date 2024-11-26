package cn.hwyee.common.media;

import lombok.SneakyThrows;

import javax.sip.PeerUnavailableException;
import javax.sip.SipFactory;
import javax.sip.SipStack;
import java.util.Properties;

/**
 * @author hui
 * @version 1.0
 * @className SipServer
 * @description
 * @date 2024/11/26
 * @since JDK 1.8
 */
public class SipServer {

//    @SneakyThrows
    public static void main(String[] args) throws Exception {
//        Class<?> aClass = Class.forName("gov.nist.javax.sip.SipStackImpl");
        //单例
        SipFactory sipFactory = SipFactory.getInstance();
        //供应商的反向域名，默认值也是gov.nist。用于寻找sipStack的实现。gb
        // Class.forName(getPathName() + ".javax.sip.SipStackImpl")
        sipFactory.setPathName("gov.nist");
        Properties properties = new Properties();
        properties.setProperty("javax.sip.IP_ADDRESS", "127.0.0.1");
        properties.setProperty("javax.sip.STACK_NAME", "hwyee");
        SipStack sipStack = sipFactory.createSipStack(properties);
        sipStack.createListeningPoint("127.0.0.1", 5060, "udp");
        sipStack.createListeningPoint("127.0.0.1", 5060, "tcp");



    }
}
