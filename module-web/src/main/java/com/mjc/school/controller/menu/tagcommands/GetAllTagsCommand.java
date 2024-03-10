package com.mjc.school.controller.menu.tagcommands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.menu.MenuOptions;
import com.mjc.school.controller.menuinterface.MenuCommands;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.springframework.stereotype.Component;

import static com.mjc.school.controller.menu.MenuInputTexts.OPERATION;

@Component
public class GetAllTagsCommand implements MenuCommands {
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;

    public GetAllTagsCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> tagController) {
        this.tagController = tagController;
    }

    @Override
    public void execute() {
        System.out.println(OPERATION.getText() + MenuOptions.GET_ALL_TAGS.getOptionName());
        tagController.readAll().forEach(System.out::println);
    }
}