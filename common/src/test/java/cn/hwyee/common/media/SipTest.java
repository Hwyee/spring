package cn.hwyee.common.media;

import gov.nist.javax.sdp.MediaDescriptionImpl;
import lombok.extern.slf4j.Slf4j;

import javax.sdp.Media;
import javax.sdp.MediaDescription;
import javax.sdp.SdpException;
import javax.sdp.SdpFactory;
import javax.sdp.SdpParseException;
import javax.sdp.SessionDescription;
import javax.sip.PeerUnavailableException;
import javax.sip.SipFactory;
import javax.sip.address.SipURI;
import java.text.ParseException;
import java.util.Vector;

/**
 * @author hui
 * @version 1.0
 * @className SipTest
 * @description
 * @date 2024/11/22
 * @since JDK 1.8
 */
@Slf4j
public class SipTest {
    public static void main(String[] args) throws SdpParseException,
            SdpException, PeerUnavailableException, ParseException {
        SipURI sipURI = SipFactory.getInstance().createAddressFactory().createSipURI("test", "localhost");

        System.out.println(sipURI.toString());//sip:test@localhost

    }

    public void test1() throws Exception {
        String sdpFields = "v=0\r\n"
                + "o=CiscoSystemsSIP-GW-UserAgent 2578 3027 IN IP4 83.211.215.216\r\n"
                + "s=SIP Call\r\n" + "c=IN IP4 62.94.199.36\r\n" + "t=0 0\r\n"
                + "m=audio 62278 RTP/AVP 18 8 0 4 3 125 101 19\r\n"
                + "c=IN IP4 62.94.199.36\r\n" + "a=rtpmap:18 G729/8000\r\n"
                + "a=fmtp:18 annexb=yes\r\n" + "a=rtpmap:8 PCMA/8000\r\n"
                + "a=rtpmap:0 PCMU/8000\r\n" + "a=rtpmap:4 G723/8000\r\n"
                + "a=fmtp:4 bitrate=5.3;annexa=no\r\n"
                + "a=rtpmap:3 GSM/8000\r\n" + "a=rtpmap:125 X-CCD/8000\r\n"
                + "a=rtpmap:101 telephone-event/8000\r\n"
                + "a=fmtp:101 0-16\r\n" + "a=rtpmap:19 CN/8000\r\n"
                + "a=direction:passive\r\n";

        SdpFactory sdpFactory = SdpFactory.getInstance();
        SessionDescription sessionDescription = sdpFactory
                .createSessionDescription(sdpFields);

        System.out.println("sessionDescription = " + sessionDescription);
        Vector mediaDescriptions = sessionDescription
                .getMediaDescriptions(true);

        for (int i = 0; i < mediaDescriptions.size(); i++) {
            MediaDescription m = (MediaDescription) mediaDescriptions
                    .elementAt(i);
            ((MediaDescriptionImpl) m).setDuplexity("sendrecv");
            System.out.println("m = " + m.toString());
            Media media = m.getMedia();
            Vector formats = media.getMediaFormats(false);
            System.out.println("formats = " + formats);
        }
    }
}
