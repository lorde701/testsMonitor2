package ru.aplana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        ApiContextInitializer.init();
//        AppTelegramBot.Companion.setup();
//        SpringApplication.run(Application.class, args);
        SpringApplication.run(Application.class, args);
    }
}
