package demo.liaopeixiong.fdiweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("demo.liaopeixiong.cdccommon.entity")
@ComponentScan(basePackages = "demo.liaopeixiong")
public class FdiWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FdiWebApplication.class, args);
    }

}
