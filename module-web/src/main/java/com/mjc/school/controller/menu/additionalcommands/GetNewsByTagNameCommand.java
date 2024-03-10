package com.mjc.school.controller.menu.additionalcommands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.impl.TagController;
import com.mjc.school.controller.menu.MenuOptions;
import com.mjc.school.controller.menuinterface.MenuCommands;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.menu.MenuInputTexts.*;

@Component
public class GetNewsByTagNameCommand implements MenuCommands {
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;
    private final Scanner scanner;

    public GetNewsByTagNameCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> tagController) {
        this.tagController = tagController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println(OPERATION.getText() + MenuOptions.GET_NEWS_BY_TAG_NAME.getOptionName());
        System.out.println(ENTER_TAG_NAME);
        String tagName = scanner.nextLine();
        System.out.println(((TagController) tagController).getNewsByTagName(tagName));
    }
}