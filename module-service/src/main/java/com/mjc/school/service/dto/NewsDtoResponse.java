package com.mjc.school.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class NewsDtoResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private AuthorDtoResponse authorDtoResponse;
    private Set<TagDtoResponse> tagDtoResponseSet;

    public NewsDtoResponse() {
    }

    public NewsDtoResponse(String title, String content, AuthorDtoResponse authorDtoResponse) {
        this.title = title;
        this.content = content;
        this.authorDtoResponse = authorDtoResponse;
    }

    public NewsDtoResponse(Long id, String title, String content,
                           LocalDateTime createDate, LocalDateTime lastUpdateDate,
                           AuthorDtoResponse authorDtoResponse, Set<TagDtoResponse> tagDtoResponseSet) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorDtoResponse = authorDtoResponse;
        this.tagDtoResponseSet = tagDtoResponseSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public AuthorDtoResponse getAuthorDtoResponse() {
        return authorDtoResponse;
    }

    public void setAuthorDtoResponse(AuthorDtoResponse authorDtoResponse) {
        this.authorDtoResponse = authorDtoResponse;
    }

    public Set<TagDtoResponse> getTagDtoResponseSet() {
        return tagDtoResponseSet;
    }

    public void setTagDtoResponseSet(Set<TagDtoResponse> tagDtoResponseSet) {
        this.tagDtoResponseSet = tagDtoResponseSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDtoResponse that = (NewsDtoResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(createDate, that.createDate) && Objects.equals(lastUpdateDate, that.lastUpdateDate) && Objects.equals(authorDtoResponse, that.authorDtoResponse) && Objects.equals(tagDtoResponseSet, that.tagDtoResponseSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdateDate, authorDtoResponse, tagDtoResponseSet);
    }

    @Override
    public String toString() {
        return "NewsDtoResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", authorDtoResponse=" + authorDtoResponse +
                ", tagDtoResponseSet=" + tagDtoResponseSet +
                '}';
    }
}