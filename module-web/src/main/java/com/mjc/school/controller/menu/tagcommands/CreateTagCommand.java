package com.mjc.school.controller.menu.tagcommands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.menu.MenuOptions;
import com.mjc.school.controller.menuinterface.MenuCommands;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.errorsexceptions.NotFoundException;
import com.mjc.school.service.errorsexceptions.ValidatorException;
import com.mjc.school.service.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.menu.MenuInputTexts.*;

@Component
public class CreateTagCommand implements MenuCommands {
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;
    private final Scanner scanner;

    public CreateTagCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> tagController) {
        this.tagController = tagController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println(OPERATION.getText() + MenuOptions.CREATE_TAG.getOptionName());
        Validator validator = new Validator();
        try {
            System.out.println(ENTER_TAG_NAME);
            String name = scanner.nextLine();

            TagDtoRequest tagDtoRequest = new TagDtoRequest(name);
            System.out.println(tagController.create(tagDtoRequest));
        } catch (ValidatorException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}