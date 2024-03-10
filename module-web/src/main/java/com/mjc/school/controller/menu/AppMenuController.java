package com.mjc.school.controller.menu;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.menu.additionalcommands.*;
import com.mjc.school.controller.menu.authorcommands.*;
import com.mjc.school.controller.menu.newscommands.*;
import com.mjc.school.controller.menu.tagcommands.*;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.errorsexceptions.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.mjc.school.controller.menu.MenuInputTexts.*;

@Component
public class AppMenuController {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;
    private final CommandsExecutor commandsExecutor;
    private final Scanner scanner;

    @Autowired
    public AppMenuController(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                             BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController,
                             BaseController<TagDtoRequest, TagDtoResponse, Long> tagController,
                             CommandsExecutor commandsExecutor) {
        this.newsController = newsController;
        this.authorController = authorController;
        this.tagController = tagController;
        this.commandsExecutor = commandsExecutor;
        scanner = new Scanner(System.in);
    }

    public void runApp() {
        while (true) {
            // program prints out app menu
            System.out.println(ENTER_NUMBER_OF_OPERATION);
            for (MenuOptions options: MenuOptions.values()) {
                switch (options.getOptionCode()) {
                    case "1" -> System.out.println("*** COMMANDS WITH NEWS ***");
                    case "6" -> System.out.println("*** COMMANDS WITH AUTHOR ***");
                    case "11" -> System.out.println("*** COMMANDS WITH TAG ***");
                    case "16" -> System.out.println("*** COMMANDS WITH ADDITIONAL PARAMETERS ***");
                }
                System.out.println(options.getOptionCode() + " - " + options.getOptionName());
            }
            // client chooses menu option
            switch (scanner.nextLine()) {
                case "1" -> commandsExecutor.executeCommand(new GetAllNewsCommand(newsController));
                case "2" -> commandsExecutor.executeCommand(new GetNewsByIdCommand(newsController));
                case "3" -> commandsExecutor.executeCommand(new CreateNewsCommand(newsController, tagController));
                case "4" -> commandsExecutor.executeCommand(new UpdateNewsCommand(newsController, tagController));
                case "5" -> commandsExecutor.executeCommand(new RemoveNewsByIdCommand(newsController));
                case "6" -> commandsExecutor.executeCommand(new GetAllAuthorsCommand(authorController));
                case "7" -> commandsExecutor.executeCommand(new GetAuthorByIdCommand(authorController));
                case "8" -> commandsExecutor.executeCommand(new CreateAuthorCommand(authorController));
                case "9" -> commandsExecutor.executeCommand(new UpdateAuthorCommand(authorController));
                case "10" -> commandsExecutor.executeCommand(new RemoveAuthorByIdCommand(authorController, newsController));
                case "11" -> commandsExecutor.executeCommand(new GetAllTagsCommand(tagController));
                case "12" -> commandsExecutor.executeCommand(new GetTagByIdCommand(tagController));
                case "13" -> commandsExecutor.executeCommand(new CreateTagCommand(tagController));
                case "14" -> commandsExecutor.executeCommand(new UpdateTagCommand(tagController));
                case "15" -> commandsExecutor.executeCommand(new RemoveTagByIdCommand(tagController));
                case "16" -> commandsExecutor.executeCommand(new GetAuthorByNewsIdCommand(newsController));
                case "17" -> commandsExecutor.executeCommand(new GetTagsByNewsIdCommand(newsController));
                case "18" -> commandsExecutor.executeCommand(new GetNewsByTitleCommand(newsController));
                case "19" -> commandsExecutor.executeCommand(new GetNewsByContentCommand(newsController));
                case "20" -> commandsExecutor.executeCommand(new GetNewsByTagIdCommand(tagController));
                case "21" -> commandsExecutor.executeCommand(new GetNewsByTagNameCommand(tagController));
                case "22" -> commandsExecutor.executeCommand(new GetNewsByAuthorNameCommand(authorController));
                case "0" -> System.exit(0);
                default -> System.out.println(Errors.ERROR_COMMAND_NOT_FOUND.getErrorMessage());
            }
        }
    }
}