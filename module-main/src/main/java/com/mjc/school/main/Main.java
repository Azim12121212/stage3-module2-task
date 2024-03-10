package com.mjc.school.main;

import com.mjc.school.controller.menu.AppMenuController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class);
        AppMenuController app = ctx.getBean(AppMenuController.class);
        app.runApp();
    }
}