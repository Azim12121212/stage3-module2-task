package com.mjc.school.repository.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tag")
public class TagModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "tagModelSet")
    private Set<NewsModel> newsModelSet = new HashSet<>();

    public TagModel() {
    }

    public TagModel(String name) {
        this.name = name;
    }

    public TagModel(Long id, String name, Set<NewsModel> newsModelSet) {
        this.id = id;
        this.name = name;
        this.newsModelSet = newsModelSet;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<NewsModel> getNewsModelSet() {
        return newsModelSet;
    }

    public void setNewsModelSet(Set<NewsModel> newsModelSet) {
        this.newsModelSet = newsModelSet;
    }

    @Override
    public String toString() {
        return "TagModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", newsModelSet=" + newsModelSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagModel tagModel = (TagModel) o;
        return Objects.equals(id, tagModel.id) && Objects.equals(name, tagModel.name) && Objects.equals(newsModelSet, tagModel.newsModelSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, newsModelSet);
    }
}
