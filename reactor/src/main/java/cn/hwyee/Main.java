package cn.hwyee;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ${NAME}
 * @description
 * @date 2024/1/28
 * @since JDK 1.8
 */// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!\n");
        /**
         * 发布者
         */
        SubmissionPublisher<String> publisher = new SubmissionPublisher<String>();

        /**
         * 订阅者
         */
        Flow.Subscriber subscriber = new Flow.Subscriber() {
            Flow.Subscription subscription;

            /**
             * onSubscribe:
             * 订阅时触发
             * @author hui
             * @version 1.0
             * @param subscription
             * @return void
             * @date 2024/1/28 0:42
             */

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println(Thread.currentThread().getName() + ":Subscriber Onsubscribed.");

                subscription.request(1);
            }

            /**
             * onNext:
             * 接收到元素触发
             * @author hui
             * @version 1.0
             * @param item
             * @return void
             * @date 2024/1/28 0:42
             */
            @Override
            public void onNext(Object item) {
                System.out.println(Thread.currentThread().getName() + ":" + item + "->Subscriber onNext.");
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(Thread.currentThread().getName() + ":Subscriber onError.");
            }

            @Override
            public void onComplete() {
                System.out.println(Thread.currentThread().getName() + ":Subscriber onComplete.");
            }
        };
        Flow.Processor processor1 = new MyProcessor(1);
        Flow.Processor processor2 = new MyProcessor(2);
        Flow.Processor processor3 = new MyProcessor(3);
        //绑定订阅关系
        publisher.subscribe(processor1);
        processor1.subscribe(processor2);
        processor2.subscribe(processor3);
        processor3.subscribe(subscriber);

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println(Thread.currentThread().getName() + ":i = " + i);
            publisher.submit("i-" + i);
            if (i == 3) {
                publisher.submit("i-" + i + 3000);
//                publisher.closeExceptionally(new RuntimeException("publish error."));
            }
        }
        Thread.sleep(10000);

    }
}

/**
 * Processor:
 * Processor 可以既是发布者，又是消费者。
 *
 * @author hui
 * @version 1.0
 * @return
 * @date 2024/1/28 14:55
 */
class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<String, String> {
    int num;

    public MyProcessor() {
    }

    public MyProcessor(int i) {
        num = i;
    }

    Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        System.out.println(Thread.currentThread().getName() + "Processor" + num + " onSubscribe.");
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        System.out.println(Thread.currentThread().getName() + "Processor" + num + " onNext.");
        item += "->processor" + num;
        subscription.request(1);
        //处理完后可以再把消息发布出去
        submit(item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(Thread.currentThread().getName() + "Processor" + num + " onNext.");
    }

    @Override
    public void onComplete() {
        System.out.println(Thread.currentThread().getName() + "Processor" + num + " onNext.");
    }
}