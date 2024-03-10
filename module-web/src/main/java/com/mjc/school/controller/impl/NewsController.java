package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.impl.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService) {
        this.newsService = newsService;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return newsService.readById(id);
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return newsService.deleteById(id);
    }

    // Get Author by news id – return author by provided news id.
    public AuthorDtoResponse getAuthorByNewsId(Long newsId) {
        return ((NewsService) newsService).getAuthorByNewsId(newsId);
    }

    // Get Tags by news id – return tags by provided news id.
    public Set<TagDtoResponse> getTagsByNewsId(Long newsId) {
        return ((NewsService) newsService).getTagsByNewsId(newsId);
    }

    // Get News by title
    public List<NewsDtoResponse> getNewsByTitle(String title) {
        return ((NewsService) newsService).getNewsByTitle(title);
    }

    // Get News by content
    public List<NewsDtoResponse> getNewsByContent(String content) {
        return ((NewsService) newsService).getNewsByContent(content);
    }
}