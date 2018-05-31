import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author liyujie
 * @Description: 测试
 * @date: 2018/5/29
 */
@SpringBootApplication
@ComponentScan("com.dubbo.test")
public class ConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class,args);
        System.out.println("消费者 ConsumerApp 启动完毕！");
    }
}
