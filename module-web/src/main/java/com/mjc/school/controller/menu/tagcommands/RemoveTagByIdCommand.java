package com.mjc.school.controller.menu.tagcommands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.menu.MenuOptions;
import com.mjc.school.controller.menuinterface.MenuCommands;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.NotFoundException;
import com.mjc.school.service.errorsexceptions.ValidatorException;
import com.mjc.school.service.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.menu.MenuInputTexts.ENTER_TAG_ID;
import static com.mjc.school.controller.menu.MenuInputTexts.OPERATION;

@Component
public class RemoveTagByIdCommand implements MenuCommands {
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;
    private final Scanner scanner;

    public RemoveTagByIdCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> tagController) {
        this.tagController = tagController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println(OPERATION.getText() + MenuOptions.REMOVE_TAG_BY_ID.getOptionName());
        Validator validator = new Validator();
        try {
            System.out.println(ENTER_TAG_ID);
            String id = scanner.nextLine();
            if (!validator.validateId(id)) {
                throw new ValidatorException(Errors.ERROR_TAG_ID_FORMAT.getErrorData("", false));
            }

            System.out.println(tagController.deleteById(Long.parseLong(id)));
        } catch (ValidatorException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}