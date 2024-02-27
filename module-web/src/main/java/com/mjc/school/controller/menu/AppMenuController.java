package com.mjc.school.controller.menu;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.annotation.ValidatingNewsId;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.NotFoundException;
import com.mjc.school.service.errorsexceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.menu.MenuInputTexts.*;

@Component
public class AppMenuController {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final Scanner scanner;

    @Autowired
    public AppMenuController(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                             BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.newsController = newsController;
        this.authorController = authorController;
        scanner = new Scanner(System.in);
    }

    public void runApp() {
        while (true) {
            // program prints out app menu
            System.out.println(ENTER_NUMBER_OF_OPERATION.getText());
            for (MenuOptions options: MenuOptions.values()) {
                System.out.println(options.getOptionCode() + " - " + options.getOptionName());
            }
            // client chooses menu option
            switch (scanner.nextLine()) {
                case "1" -> getAllNewsOption();
                case "2" -> getNewsByIdOption(scanner);
                case "0" -> System.exit(0);
                default -> System.out.println(Errors.ERROR_COMMAND_NOT_FOUND.getErrorMessage());
            }
        }
    }

    private void getAllNewsOption() {
        System.out.println(OPERATION.getText() + MenuOptions.GET_ALL_NEWS.getOptionName());
        newsController.readAll().forEach(System.out::println);
    }

    private void getNewsByIdOption(@ValidatingNewsId Scanner scanner) {
        System.out.println(OPERATION.getText() + MenuOptions.GET_NEWS_BY_ID.getOptionName());
        try {
            System.out.println(ENTER_NEWS_ID.getText());
            String newsId = scanner.nextLine();
            System.out.println(newsController.readById(Long.parseLong(newsId)));
        } catch (ValidatorException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}