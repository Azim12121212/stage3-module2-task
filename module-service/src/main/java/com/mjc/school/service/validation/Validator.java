package com.mjc.school.service.validation;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.ValidatorException;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final int NEWS_TITLE_MIN = 5;
    private static final int NEWS_TITLE_MAX = 30;
    private static final int NEWS_CONTENT_MIN = 5;
    private static final int NEWS_CONTENT_MAX = 255;
    private static final int AUTHOR_NAME_MIN = 3;
    private static final int AUTHOR_NAME_MAX = 15;

    public void validateNewsDtoRequest(NewsDtoRequest newsDtoRequest) {
        validateNewsId(String.valueOf(newsDtoRequest.getId()));
        validateNewsTitle(newsDtoRequest.getTitle());
        validateNewsContent(newsDtoRequest.getContent());
        validateNewsAuthorId(String.valueOf(newsDtoRequest.getAuthorId()));
    }

    public void validateAuthorDtoRequest(AuthorDtoRequest authorDtoRequest) {
        validateAuthorId(String.valueOf(authorDtoRequest.getId()));
        validateAuthorName(authorDtoRequest.getName());
    }

    public void validateNewsId(String newsId) {
        if (!validateId(newsId)) {
            throw new ValidatorException(Errors.ERROR_NEWS_ID_FORMAT.getErrorData("", false));
        }
        if (newsId==null || Long.parseLong(newsId)<1) {
            throw new ValidatorException(Errors.ERROR_NEWS_ID_VALUE.getErrorData(newsId, false));
        }
    }

    private void validateNewsTitle(String title) {
        if (title.length()<NEWS_TITLE_MIN || title.length()>NEWS_TITLE_MAX) {
            throw new ValidatorException(Errors.ERROR_NEWS_TITLE_LENGTH.getErrorData(title, false));
        }
    }

    private void validateNewsContent(String content) {
        if (content.length()<NEWS_CONTENT_MIN || content.length()>NEWS_CONTENT_MAX) {
            throw new ValidatorException(Errors.ERROR_NEWS_CONTENT_LENGTH.getErrorData(content, false));
        }
    }

    private void validateNewsAuthorId(String newsAuthorId) {
        if (!validateId(newsAuthorId)) {
            throw new ValidatorException(Errors.ERROR_NEWS_AUTHOR_ID_FORMAT.getErrorData("", false));
        }
        if (newsAuthorId==null || Long.parseLong(newsAuthorId)<1) {
            throw new ValidatorException(Errors.ERROR_NEWS_AUTHOR_ID_VALUE.getErrorData(newsAuthorId, false));
        }
    }

    public void validateAuthorId(String authorId) {
        if (!validateId(authorId)) {
            throw new ValidatorException(Errors.ERROR_AUTHOR_ID_FORMAT.getErrorData("", false));
        }
        if (authorId==null || Long.parseLong(authorId)<1) {
            throw new ValidatorException(Errors.ERROR_AUTHOR_ID_VALUE.getErrorData(authorId, false));
        }
    }

    private void validateAuthorName(String name) {
        if (name.length()<AUTHOR_NAME_MIN || name.length()>AUTHOR_NAME_MAX) {
            throw new ValidatorException(Errors.ERROR_AUTHOR_NAME_LENGTH.getErrorData(name, false));
        }
    }

    private boolean validateId(String id) {
        char[] chars = id.toCharArray();
        int counter=0;
        if (chars[0]=='-') {
            counter++;
        }
        for (int i=0; i<chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                counter++;
            }
        }
        return counter==chars.length;
    }
}