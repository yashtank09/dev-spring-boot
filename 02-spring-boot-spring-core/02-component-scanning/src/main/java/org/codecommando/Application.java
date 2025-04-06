package org.codecommando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.codewar.util",
                "org.codecommando"
        }
) // Explicitly listing packages for spring component scanning
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
