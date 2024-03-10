package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
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

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {
    private final DateTimeFormatter MY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthorRepository() {
    }

    @Override
    public List<AuthorModel> readAll() {
        return entityManager.createQuery("SELECT a FROM AuthorModel a", AuthorModel.class).getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.of(entityManager.find(AuthorModel.class, id));
    }

    @Transactional
    @Override
    public AuthorModel create(AuthorModel entity) {
        LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(MY_FORMAT));
        entity.setCreateDate(dateTime);
        entity.setLastUpdateDate(dateTime);
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public AuthorModel update(AuthorModel entity) {
        LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(MY_FORMAT));
        AuthorModel authorModel = null;
        if (existById(entity.getId())) {
            authorModel = entityManager.find(AuthorModel.class, entity.getId());
            authorModel.setName(entity.getName());
            authorModel.setLastUpdateDate(dateTime);
            entityManager.merge(authorModel);
        }
        return authorModel;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            entityManager.remove(entityManager.find(AuthorModel.class, id));
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.getReference(AuthorModel.class, id)!=null;
    }

    // Get News by author name
    public List<NewsModel> getNewsByAuthorName(String name) {
        AuthorModel authorModel = (AuthorModel) entityManager
                .createQuery("SELECT a FROM AuthorModel a WHERE a.name = ?1")
                .setParameter(1, name).getSingleResult();
        return authorModel.getNewsModelList();
    }
}