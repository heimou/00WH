import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author liyujie
 * @Description: 生产者启动类
 * @date: 2018/5/29
 */
@SpringBootApplication
@ComponentScan("com.test.dubbo.service.impl")
public class ProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApp.class,args);
        System.out.println("生产者ProviderApp 启动完毕！");
    }
}
