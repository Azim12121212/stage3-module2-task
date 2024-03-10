package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.TagRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidatingTag;
import com.mjc.school.service.annotation.ValidatingTagId;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements BaseService<TagDtoRequest, TagDtoResponse, Long> {
    private final BaseRepository<TagModel, Long> tagRepository;

    @Autowired
    public TagService(BaseRepository<TagModel, Long> tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagDtoResponse> readAll() {
        return MyMapper.INSTANCE.tagModelListToTagDtoList(tagRepository.readAll());
    }

    @ValidatingTagId
    @Override
    public TagDtoResponse readById(Long id) {
        return tagRepository.readById(id)
                .map(MyMapper.INSTANCE::tagModelToTagDto)
                .orElseThrow(() -> new NotFoundException(Errors.ERROR_TAG_ID_NOT_EXIST.getErrorData(String.valueOf(id), true)));
    }

    @ValidatingTag
    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        TagModel tagModel = tagRepository.create(MyMapper.INSTANCE.tagDtoToTagModel(createRequest));
        return MyMapper.INSTANCE.tagModelToTagDto(tagModel);
    }

    @ValidatingTag
    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        if (readById(updateRequest.getId())!=null) {
            TagModel tagModel = tagRepository.update(MyMapper.INSTANCE.tagDtoToTagModel(updateRequest));
            return MyMapper.INSTANCE.tagModelToTagDto(tagModel);
        }
        return null;
    }

    @ValidatingTagId
    @Override
    public boolean deleteById(Long id) {
        if (readById(id)!=null) {
            return tagRepository.deleteById(id);
        } else {
            return false;
        }
    }

    // Get News by tag id
    @ValidatingTagId
    public List<NewsDtoResponse> getNewsByTagId(Long id) {
        List<NewsModel> newsModelList = ((TagRepository) tagRepository).getNewsByTagId(id);
        return MyMapper.INSTANCE.newsModelListToNewsDtoList(newsModelList);
    }

    // Get News by tag name
    public List<NewsDtoResponse> getNewsByTagName(String name) {
        List<NewsModel> newsModelList = ((TagRepository) tagRepository).getNewsByTagName(name);
        return MyMapper.INSTANCE.newsModelListToNewsDtoList(newsModelList);
    }
}