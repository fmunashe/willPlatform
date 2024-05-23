package zw.co.zim.willplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class WillPlatformCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(WillPlatformCoreApplication.class, args);
    }
}
