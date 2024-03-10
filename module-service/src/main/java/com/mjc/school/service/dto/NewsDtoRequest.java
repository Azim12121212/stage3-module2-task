package com.mjc.school.service.dto;

import java.util.List;

public class NewsDtoRequest {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private List<Long> tagIdList;

    public NewsDtoRequest() {
    }

    public NewsDtoRequest(String title, String content, Long authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public NewsDtoRequest(String title, String content, Long authorId, List<Long> tagIdList) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.tagIdList = tagIdList;
    }

    public NewsDtoRequest(Long id, String title, String content, Long authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public NewsDtoRequest(Long id, String title, String content, Long authorId, List<Long> tagIdList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.tagIdList = tagIdList;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public List<Long> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Long> tagIdList) {
        this.tagIdList = tagIdList;
    }
}