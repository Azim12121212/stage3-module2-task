package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    private final DateTimeFormatter MY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public NewsRepository() {
    }

    @Override
    public List<NewsModel> readAll() {
        return entityManager.createQuery("SELECT n FROM NewsModel n", NewsModel.class).getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(NewsModel.class, id));
    }

    @Transactional
    @Override
    public NewsModel create(NewsModel entity) {
        LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(MY_FORMAT));
        entity.setCreateDate(dateTime);
        entity.setLastUpdateDate(dateTime);
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public NewsModel update(NewsModel entity) {
        LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(MY_FORMAT));
        NewsModel newsModel = null;
        if (existById(entity.getId())) {
            newsModel = entityManager.find(NewsModel.class, entity.getId());
            newsModel.setTitle(entity.getTitle());
            newsModel.setContent(entity.getContent());
            newsModel.setLastUpdateDate(dateTime);
            newsModel.setAuthorModel(entity.getAuthorModel());
            entityManager.merge(newsModel);
        }
        return newsModel;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            entityManager.remove(entityManager.find(NewsModel.class, id));
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.getReference(NewsModel.class, id)!=null;
    }

    // Get Author by news id – return author by provided news id.
    public AuthorModel getAuthorByNewsId(Long newsId) {
        NewsModel newsModel = readById(newsId).get();
        return newsModel.getAuthorModel();
    }

    // Get Tags by news id – return tags by provided news id.
    public Set<TagModel> getTagsByNewsId(Long newsId) {
        NewsModel newsModel = readById(newsId).get();
        return newsModel.getTagModelSet();
    }

    // Get News by title
    public List<NewsModel> getNewsByTitle(String title) {
        Query query = entityManager.createQuery("SELECT n FROM NewsModel n WHERE c.title LIKE :title")
                .setParameter("title", title);
        List<NewsModel> newsModelList = query.getResultList();
        return newsModelList;
    }

    // Get News by content
    public List<NewsModel> getNewsByContent(String content) {
        Query query = entityManager.createQuery("SELECT n FROM NewsModel n WHERE c.content LIKE :content")
                .setParameter("content", "%" + content + "%");
        List<NewsModel> newsModelList = query.getResultList();
        return newsModelList;
    }
}