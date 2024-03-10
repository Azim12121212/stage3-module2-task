package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidatingNews;
import com.mjc.school.service.annotation.ValidatingNewsId;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    private final BaseRepository<NewsModel, Long> newsRepository;
    private final BaseRepository<AuthorModel, Long> authorRepository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository,
                       BaseRepository<AuthorModel, Long> authorRepository) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return MyMapper.INSTANCE.newsModelListToNewsDtoList(newsRepository.readAll());
    }

    @ValidatingNewsId
    @Override
    public NewsDtoResponse readById(Long id) {
        return newsRepository.readById(id)
                .map(MyMapper.INSTANCE::newsModelToNewsDto)
                .orElseThrow(() -> new NotFoundException(Errors.ERROR_NEWS_ID_NOT_EXIST.getErrorData(String.valueOf(id), true)));
    }

    @ValidatingNews
    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        boolean equal = false;
        for (AuthorModel authorModel: authorRepository.readAll()) {
            if (Objects.equals(createRequest.getAuthorId(), authorModel.getId())) {
                equal = true;
            }
        }
        if (equal) {
            NewsModel newsModel = newsRepository.create(MyMapper.INSTANCE.newsDtoToNewsModel(createRequest));
            return MyMapper.INSTANCE.newsModelToNewsDto(newsModel);
        }
        return null;
    }

    @ValidatingNews
    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        for (AuthorModel authorModel: authorRepository.readAll()) {
            if (Objects.equals(updateRequest.getAuthorId(), authorModel.getId())
                    && readById(updateRequest.getId())!=null) {
                NewsModel newsModel = newsRepository.update(MyMapper.INSTANCE.newsDtoToNewsModel(updateRequest));
                return MyMapper.INSTANCE.newsModelToNewsDto(newsModel);
            }
        }
        return null;
    }

    @ValidatingNewsId
    @Override
    public boolean deleteById(Long id) {
        if (readById(id)!=null) {
            return newsRepository.deleteById(id);
        } else {
            return false;
        }
    }

    // Get Author by news id – return author by provided news id.
    @ValidatingNewsId
    public AuthorDtoResponse getAuthorByNewsId(Long newsId) {
        AuthorModel authorModel = ((NewsRepository) newsRepository).getAuthorByNewsId(newsId);
        return MyMapper.INSTANCE.authorModelToAuthorDto(authorModel);
    }

    // Get Tags by news id – return tags by provided news id.
    @ValidatingNewsId
    public Set<TagDtoResponse> getTagsByNewsId(Long newsId) {
        Set<TagModel> tagModelSet = ((NewsRepository) newsRepository).getTagsByNewsId(newsId);
        return MyMapper.INSTANCE.tagModelSetToTagDtoSet(tagModelSet);
    }

    // Get News by title
    public List<NewsDtoResponse> getNewsByTitle(String title) {
        List<NewsModel> newsModelList = ((NewsRepository) newsRepository).getNewsByTitle(title);
        return MyMapper.INSTANCE.newsModelListToNewsDtoList(newsModelList);
    }

    // Get News by content
    public List<NewsDtoResponse> getNewsByContent(String content) {
        List<NewsModel> newsModelList = ((NewsRepository) newsRepository).getNewsByContent(content);
        return MyMapper.INSTANCE.newsModelListToNewsDtoList(newsModelList);
    }
}