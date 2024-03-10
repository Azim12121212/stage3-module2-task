package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.*;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@org.mapstruct.Mapper
public interface Mapper {
    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    NewsDtoResponse newsModelToNewsDto(NewsModel newsModel);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "authorModel.id", source = "authorId")
    @Mapping(target = "tagModelSet", ignore = true)
    NewsModel newsDtoToNewsModel(NewsDtoRequest newsDto);

    List<NewsDtoResponse> newsModelListToNewsDtoList(List<NewsModel> newsModelList);

    AuthorDtoResponse authorModelToAuthorDto(AuthorModel authorModel);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "newsModelList", ignore = true)
    AuthorModel authorDtoToAuthorModel(AuthorDtoRequest authorDto);

    List<AuthorDtoResponse> authorModelListToAuthorDtoList(List<AuthorModel> authorModelList);

    TagDtoResponse tagModelToTagDto(TagModel tagModel);

    @Mapping(target = "newsModelSet", ignore = true)
    TagModel tagDtoToTagModel(TagDtoRequest tagDtoRequest);

    List<TagDtoResponse> tagModelListToTagDtoList(List<TagModel> tagModelList);

    Set<TagDtoResponse> tagModelSetToTagDtoSet(Set<TagModel> tagModelSet);
}