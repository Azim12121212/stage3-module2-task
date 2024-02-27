package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataSource {
    private static final int MAX_AMOUNT_OF_NEWS = 20;
    private final String AUTHOR_PATH = "module-repository/src/main/resources/authors";
    private final String CONTENT_PATH = "module-repository/src/main/resources/content";
    private final String NEWS_PATH = "module-repository/src/main/resources/news";

    private List<AuthorModel> authorModelList;
    private List<NewsModel> newsModelList;

    @Autowired
    public DataSource() {
        fillAuthorModelList();
        fillNewsModelList();
    }

    public List<AuthorModel> getAuthorList() {
        return authorModelList;
    }

    public List<NewsModel> getNewsList() {
        return newsModelList;
    }

    private void fillAuthorModelList() {
        authorModelList = new ArrayList<>();
        List<String> authorNames = MyUtils.getTextListFromFile(AUTHOR_PATH);
        for (int i=0; i<MAX_AMOUNT_OF_NEWS; i++) {
            String name = MyUtils.getRandomText(authorNames);
            LocalDateTime dateTime = MyUtils.getRandomDateTime();
            AuthorModel authorModel = new AuthorModel((long) (i+1), name, dateTime, dateTime);
            authorModelList.add(authorModel);
        }
    }

    private void fillNewsModelList() {
        newsModelList = new ArrayList<>();
        Random random = new Random();
        List<String> newsTitles = MyUtils.getTextListFromFile(NEWS_PATH);
        List<String> newsContents = MyUtils.getTextListFromFile(CONTENT_PATH);
        for (int i=0; i<MAX_AMOUNT_OF_NEWS; i++) {
            String title = MyUtils.getRandomText(newsTitles);
            String content = MyUtils.getRandomText(newsContents);
            LocalDateTime dateTime = MyUtils.getRandomDateTime();
            Long randomAuthorId = random.nextLong((authorModelList.size()-1L) + 1L) + 1L;
            NewsModel newsModel = new NewsModel((long) (i+1), title, content,
                    dateTime, dateTime, randomAuthorId);
            newsModelList.add(newsModel);
        }
    }
}