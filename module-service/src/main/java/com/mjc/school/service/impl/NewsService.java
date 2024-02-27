package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidatingNews;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    private final BaseRepository<NewsModel, Long> newsRepository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return MyMapper.INSTANCE.newsModelListToNewsDtoList(newsRepository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return newsRepository.readById(id)
                .map(MyMapper.INSTANCE::newsModelToNewsDto)
                .orElseThrow(() -> new NotFoundException(Errors.ERROR_NEWS_ID_NOT_EXIST.getErrorData(String.valueOf(id), true)));
    }

    @ValidatingNews
    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        NewsModel newsModel = newsRepository.create(MyMapper.INSTANCE.newsDtoToNewsModel(createRequest));
        return MyMapper.INSTANCE.newsModelToNewsDto(newsModel);
    }

    @ValidatingNews
    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        NewsModel newsModel = newsRepository.update(MyMapper.INSTANCE.newsDtoToNewsModel(updateRequest));
        return MyMapper.INSTANCE.newsModelToNewsDto(newsModel);
    }

    @Override
    public boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }
}