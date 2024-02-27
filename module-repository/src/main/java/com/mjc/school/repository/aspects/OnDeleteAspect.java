package com.mjc.school.repository.aspects;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OnDeleteAspect {
    private BaseRepository<NewsModel, Long> newsRepository;

    @Autowired
    public OnDeleteAspect(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @After("@annotation(com.mjc.school.repository.annotation.OnDelete) && args(authorId)")
    public void deleteRelatedNews(Long authorId) {
        boolean isAnyNews = false;
        for (NewsModel newsModel: newsRepository.readAll()) {
            if (authorId.equals(newsModel.getAuthorId())) {
                newsRepository.readAll().remove(newsModel);
                isAnyNews = true;
            }
        }
        if (isAnyNews)
            System.out.println("All the related news has been deleted!");
    }
}