package test.task.schoolregistry;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolRegistryApplication.class, args);
    }

    @Bean
    public CommandLineRunner onStartup() {
        return args -> {
            System.out.println("Усі сервіси запущено!");
            System.out.println("Відкрийте браузер: http://localhost:3000");
        };
    }

}
