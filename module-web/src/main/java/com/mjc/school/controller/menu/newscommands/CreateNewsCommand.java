package com.mjc.school.controller.menu.newscommands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.menu.MenuOptions;
import com.mjc.school.controller.menuinterface.MenuCommands;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.NotFoundException;
import com.mjc.school.service.errorsexceptions.ValidatorException;
import com.mjc.school.service.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.mjc.school.controller.menu.MenuInputTexts.*;

@Component
public class CreateNewsCommand implements MenuCommands {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;
    private final Scanner scanner;

    public CreateNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                             BaseController<TagDtoRequest, TagDtoResponse, Long> tagController) {
        this.newsController = newsController;
        this.tagController = tagController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println(OPERATION.getText() + MenuOptions.CREATE_NEWS.getOptionName());
        Validator validator = new Validator();
        try {
            System.out.println(ENTER_NEWS_TITLE);
            String title = scanner.nextLine();
            System.out.println(ENTER_NEWS_CONTENT);
            String content = scanner.nextLine();

            System.out.println(ENTER_NEWS_AUTHOR_ID);
            String authorId = scanner.nextLine();
            if (!validator.validateId(authorId)) {
                throw new ValidatorException(Errors.ERROR_NEWS_AUTHOR_ID_FORMAT.getErrorData("", false));
            }

            NewsDtoRequest newsDtoRequest;

            System.out.println("Would you like to enter tag id? (Enter 'y' if yes, or any other symbol to proceed further)");
            if (scanner.nextLine().toLowerCase().equals("y")) {
                System.out.println(ENTER_TAG_ID);
                String tagId = scanner.nextLine();
                if (!validator.validateId(tagId)) {
                    throw new ValidatorException(Errors.ERROR_TAG_ID_FORMAT.getErrorData("", false));
                }
                validator.validateTagId(Long.parseLong(tagId));

                List<Long> tagIdList = new ArrayList<>(tagController.readAll().size());
                for (TagDtoResponse tagDto: tagController.readAll()) {
                    tagIdList.add(tagDto.getId());
                }
                tagIdList.add(Long.parseLong(tagId));

                newsDtoRequest = new NewsDtoRequest(title, content,
                        Long.parseLong(authorId), tagIdList);
            } else {
                newsDtoRequest = new NewsDtoRequest(title, content, Long.parseLong(authorId));
            }

            if (newsController.create(newsDtoRequest)!=null) {
                System.out.println(newsController.create(newsDtoRequest));
            } else {
                throw new NotFoundException(Errors.ERROR_NEWS_AUTHOR_ID_NOT_EXIST.getErrorData(authorId, false));
            }
        } catch (ValidatorException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}