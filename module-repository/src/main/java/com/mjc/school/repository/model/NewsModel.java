package com.mjc.school.repository.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "news")
public class NewsModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "createdate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createDate;
    @Column(name = "lastupdatedate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime lastUpdateDate;
    @ManyToOne
    @JoinColumn(name = "authorid", nullable = false)
    private AuthorModel authorModel;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "news_tag",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagModel> tagModelSet = new HashSet<>();

    public NewsModel() {
    }

    public NewsModel(String title, String content, AuthorModel authorModel) {
        this.title = title;
        this.content = content;
        this.authorModel = authorModel;
    }

    public NewsModel(String title, String content, AuthorModel authorModel, Set<TagModel> tagModelSet) {
        this.title = title;
        this.content = content;
        this.authorModel = authorModel;
        this.tagModelSet = tagModelSet;
    }

    public NewsModel(Long id, String title, String content, LocalDateTime createDate,
                     LocalDateTime lastUpdateDate, AuthorModel authorModel, Set<TagModel> tagModelSet) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorModel = authorModel;
        this.tagModelSet = tagModelSet;
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

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }

    public Set<TagModel> getTagModelSet() {
        return tagModelSet;
    }

    public void setTagModelSet(Set<TagModel> tagModelSet) {
        this.tagModelSet = tagModelSet;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", authorModel=" + authorModel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsModel newsModel = (NewsModel) o;
        return Objects.equals(id, newsModel.id) && Objects.equals(title, newsModel.title) && Objects.equals(content, newsModel.content) && Objects.equals(createDate, newsModel.createDate) && Objects.equals(lastUpdateDate, newsModel.lastUpdateDate) && Objects.equals(authorModel, newsModel.authorModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdateDate, authorModel);
    }
}