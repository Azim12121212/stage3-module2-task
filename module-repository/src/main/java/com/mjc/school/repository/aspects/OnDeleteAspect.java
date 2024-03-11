package com.mjc.school.repository.aspects;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OnDeleteAspect {
    private final BaseRepository<NewsModel, Long> newsRepository;

    @Autowired
    public OnDeleteAspect(BaseRepository<NewsModel, Long> newsRepository) {
        this.newsRepository = newsRepository;
    }

    @After("@annotation(com.mjc.school.repository.annotation.OnDelete) && args(authorId)")
    public void deleteRelatedNews(Long authorId) {
        boolean isAnyNews = true;
        for (int i=0; i<newsRepository.readAll().size(); i++) {
            if (authorId.equals(newsRepository.readAll().get(i).getAuthorId())) {
                newsRepository.readAll().get(i).setAuthorId(null);
                isAnyNews = true;
            }
        }
        newsRepository.readAll().removeIf(newsModel -> newsModel.getAuthorId()==null);
        if (isAnyNews)
            System.out.println("All the related news has been deleted!");
    }
}