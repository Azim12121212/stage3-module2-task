package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidatingAuthor;
import com.mjc.school.service.annotation.ValidatingAuthorId;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseRepository<AuthorModel, Long> authorRepository;

    @Autowired
    public AuthorService(BaseRepository<AuthorModel, Long> authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return MyMapper.INSTANCE.authorModelListToAuthorDtoList(authorRepository.readAll());
    }

    @ValidatingAuthorId
    @Override
    public AuthorDtoResponse readById(Long id) {
        return authorRepository.readById(id)
                .map(MyMapper.INSTANCE::authorModelToAuthorDto)
                .orElseThrow(() -> new NotFoundException(Errors.ERROR_AUTHOR_ID_NOT_EXIST.getErrorData(String.valueOf(id), true)));
    }

    @ValidatingAuthor
    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        AuthorModel authorModel = authorRepository.create(MyMapper.INSTANCE.authorDtoToAuthorModel(createRequest));
        return MyMapper.INSTANCE.authorModelToAuthorDto(authorModel);
    }

    @ValidatingAuthor
    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        if (readById(updateRequest.getId())!=null) {
            AuthorModel authorModel = authorRepository.update(MyMapper.INSTANCE.authorDtoToAuthorModel(updateRequest));
            return MyMapper.INSTANCE.authorModelToAuthorDto(authorModel);
        }
        return null;
    }

    @ValidatingAuthorId
    @Override
    public boolean deleteById(Long id) {
        if (readById(id)!=null) {
            return authorRepository.deleteById(id);
        } else {
            return false;
        }
    }

    // Get News by author name
    public List<NewsDtoResponse> getNewsByAuthorName(String name) {
        List<NewsModel> newsModelList = ((AuthorRepository) authorRepository).getNewsByAuthorName(name);
        return MyMapper.INSTANCE.newsModelListToNewsDtoList(newsModelList);
    }
}