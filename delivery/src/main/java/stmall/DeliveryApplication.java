package stmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import stmall.config.kafka.KafkaProcessor;

@Configuration
@ImportResource("classpath*:egovframework/spring/context-*.xml")
@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class DeliveryApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext =
            SpringApplication.run(DeliveryApplication.class, args);
    }
}
