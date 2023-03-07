package es.iesrafaelalberti.clasesspring2223;

import es.iesrafaelalberti.clasesspring2223.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ClasesSpring2223Application {

    public static void main(String[] args) {
        SpringApplication.run(ClasesSpring2223Application.class, args);
    }

}
