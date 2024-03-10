package com.mjc.school.controller.menu.additionalcommands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.menu.MenuOptions;
import com.mjc.school.controller.menuinterface.MenuCommands;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.menu.MenuInputTexts.ENTER_AUTHOR_NAME;
import static com.mjc.school.controller.menu.MenuInputTexts.OPERATION;

@Component
public class GetNewsByAuthorNameCommand implements MenuCommands {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final Scanner scanner;

    public GetNewsByAuthorNameCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.authorController = authorController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println(OPERATION.getText() + MenuOptions.GET_NEWS_BY_AUTHOR_NAME.getOptionName());
        System.out.println(ENTER_AUTHOR_NAME);
        String authorName = scanner.nextLine();
        System.out.println(((AuthorController) authorController).getNewsByAuthorName(authorName));
    }
}