package tcc.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
@ImportResource("classpath:security.xml")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(tcc.poc.App.class, args);
    }

}
