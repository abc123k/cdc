package demo.liaopeixiong.cdcwriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "demo.liaopeixiong")
public class CdcWriterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdcWriterApplication.class, args);
    }

}
