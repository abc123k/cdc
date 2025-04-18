package demo.liaopeixiong.fdiweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("demo.liaopeixiong.cdccommon.entity")
@EnableJpaAuditing
@EnableJpaRepositories("demo.liaopeixiong.cdccommon.dao")
@ComponentScan(basePackages = "demo.liaopeixiong")
public class FdiWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FdiWebApplication.class, args);
    }

}
