package com.mjc.school.service.impl;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyMapper implements Mapper {
    @Override
    public NewsDtoResponse newsModelToNewsDto(NewsModel newsModel) {
        if (newsModel==null) {
            return null;
        }
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse(newsModel.getId(), newsModel.getTitle(),
                newsModel.getContent(), newsModel.getCreateDate(), newsModel.getLastUpdateDate(),
                authorModelToAuthorDto(newsModel.getAuthorModel()), tagModelSetToTagDtoSet(newsModel.getTagModelSet()));
        return newsDtoResponse;
    }

    @Override
    public NewsModel newsDtoToNewsModel(NewsDtoRequest newsDto) {
        if (newsDto==null) {
            return null;
        }
        NewsModel newsModel = new NewsModel(newsDto.getTitle(), newsDto.getContent(),
                mapIdToAuthor(newsDto.getAuthorId()), mapTagIdListToTagModelSet(newsDto.getTagIdList()));
        return newsModel;
    }

    @Override
    public List<NewsDtoResponse> newsModelListToNewsDtoList(List<NewsModel> newsModelList) {
        if (newsModelList==null) {
            return null;
        }
        List<NewsDtoResponse> newsDtoResponseList = new ArrayList<>(newsModelList.size());
        for (NewsModel newsModel: newsModelList) {
            newsDtoResponseList.add(newsModelToNewsDto(newsModel));
        }
        return newsDtoResponseList;
    }

    @Override
    public AuthorDtoResponse authorModelToAuthorDto(AuthorModel authorModel) {
        if (authorModel==null) {
            return null;
        }
        List<NewsDtoResponse> newsDtoResponseList = new ArrayList<>(authorModel.getNewsModelList().size());
        for (NewsModel newsModel: authorModel.getNewsModelList()) {
            newsDtoResponseList.add(newsModelToNewsDto(newsModel));
        }
        AuthorDtoResponse authorDto = new AuthorDtoResponse(authorModel.getId(), authorModel.getName(),
                authorModel.getCreateDate(), authorModel.getLastUpdateDate(), newsDtoResponseList);
        return authorDto;
    }

    @Override
    public AuthorModel authorDtoToAuthorModel(AuthorDtoRequest authorDto) {
        if (authorDto==null) {
            return null;
        }
        AuthorModel authorModel = new AuthorModel(authorDto.getName());
        return authorModel;
    }

    @Override
    public List<AuthorDtoResponse> authorModelListToAuthorDtoList(List<AuthorModel> authorModelList) {
        if (authorModelList==null) {
            return null;
        }
        List<AuthorDtoResponse> authorDtoResponseList = new ArrayList<>(authorModelList.size());
        for (AuthorModel authorModel: authorModelList) {
            authorDtoResponseList.add(authorModelToAuthorDto(authorModel));
        }
        return authorDtoResponseList;
    }

    @Override
    public TagDtoResponse tagModelToTagDto(TagModel tagModel) {
        if (tagModel==null) {
            return null;
        }
        Set<NewsDtoResponse> newsDtoResponseSet = new HashSet<>(tagModel.getNewsModelSet().size());
        for (NewsModel newsModel: tagModel.getNewsModelSet()) {
            newsDtoResponseSet.add(newsModelToNewsDto(newsModel));
        }
        TagDtoResponse tagDtoResponse = new TagDtoResponse(tagModel.getId(), tagModel.getName(), newsDtoResponseSet);
        return tagDtoResponse;
    }

    @Override
    public TagModel tagDtoToTagModel(TagDtoRequest tagDtoRequest) {
        if (tagDtoRequest==null) {
            return null;
        }
        TagModel tagModel = new TagModel(tagDtoRequest.getName());
        return tagModel;
    }

    @Override
    public List<TagDtoResponse> tagModelListToTagDtoList(List<TagModel> tagModelList) {
        if (tagModelList==null) {
            return null;
        }
        List<TagDtoResponse> tagDtoResponseList = new ArrayList<>(tagModelList.size());
        for (TagModel tagModel: tagModelList) {
            tagDtoResponseList.add(tagModelToTagDto(tagModel));
        }
        return tagDtoResponseList;
    }

    @Override
    public Set<TagDtoResponse> tagModelSetToTagDtoSet(Set<TagModel> tagModelSet) {
        if (tagModelSet==null) {
            return null;
        }
        Set<TagDtoResponse> tagDtoResponseSet = new HashSet<>(tagModelSet.size());
        for (TagModel tagModel: tagModelSet) {
            tagDtoResponseSet.add(tagModelToTagDto(tagModel));
        }
        return tagDtoResponseSet;
    }

    public AuthorModel mapIdToAuthor(Long authorId) {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(authorId);
        return authorModel;
    }

    public Set<TagModel> mapTagIdListToTagModelSet(List<Long> tagIdList) {
        Set<TagModel> tagModelSet = new HashSet<>(tagIdList.size());
        for (Long tagId: tagIdList) {
            TagModel tagModel = new TagModel();
            tagModel.setId(tagId);
            tagModelSet.add(tagModel);
        }
        return tagModelSet;
    }
}