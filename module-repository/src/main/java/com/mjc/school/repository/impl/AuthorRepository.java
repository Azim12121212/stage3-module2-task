package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotation.OnDelete;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {
    private final DateTimeFormatter MY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private final DataSource dataSource;

    @Autowired
    public AuthorRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthorList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.of(readAll()
                .stream()
                .filter(authorModel -> id.equals(authorModel.getId()))
                .findFirst())
                .orElse(null);
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        Long id = 1L;
        if (!readAll().isEmpty()) {
            id = readAll().get(readAll().size()-1).getId() + 1;
        }
        LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(MY_FORMAT));
        entity.setId(id);
        entity.setCreateDate(dateTime);
        entity.setLastUpdateDate(dateTime);
        readAll().add(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(MY_FORMAT));

        int index = -1;
        for (AuthorModel authorModel: readAll()) {
            if (Objects.equals(entity.getId(), authorModel.getId())) {
                index = readAll().indexOf(authorModel);
            }
        }

        if (index>=0) {
            readAll().get(index).setName(entity.getName());
            readAll().get(index).setLastUpdateDate(dateTime);
            return readAll().get(index);
        }
        return null;
    }

    @OnDelete
    @Override
    public boolean deleteById(Long id) {
        return readById(id)
                .map(authorModel -> readAll().remove(authorModel))
                .orElse(false);
    }

    @Override
    public boolean existById(Long id) {
        return readAll()
                .stream()
                .anyMatch(authorModel -> id.equals(authorModel.getId()));
    }
}