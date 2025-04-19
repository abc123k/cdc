package demo.liaopeixiong.cdcreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "demo.liaopeixiong")
public class CdcReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdcReaderApplication.class, args);
    }

}
