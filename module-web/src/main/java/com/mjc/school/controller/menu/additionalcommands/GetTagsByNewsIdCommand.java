package com.mjc.school.controller.menu.additionalcommands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.menu.MenuOptions;
import com.mjc.school.controller.menuinterface.MenuCommands;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.NotFoundException;
import com.mjc.school.service.errorsexceptions.ValidatorException;
import com.mjc.school.service.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.menu.MenuInputTexts.ENTER_NEWS_ID;
import static com.mjc.school.controller.menu.MenuInputTexts.OPERATION;

@Component
public class GetTagsByNewsIdCommand implements MenuCommands {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final Scanner scanner;

    public GetTagsByNewsIdCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController) {
        this.newsController = newsController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println(OPERATION.getText() + MenuOptions.GET_TAGS_BY_NEWS_ID.getOptionName());
        Validator validator = new Validator();
        try {
            System.out.println(ENTER_NEWS_ID);
            String newsId = scanner.nextLine();
            if (!validator.validateId(newsId)) {
                throw new ValidatorException(Errors.ERROR_NEWS_ID_FORMAT.getErrorData("", false));
            }

            System.out.println(((NewsController) newsController).getTagsByNewsId(Long.parseLong(newsId)));
        } catch (ValidatorException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}