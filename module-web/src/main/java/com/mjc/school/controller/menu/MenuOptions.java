package com.mjc.school.controller.menu;

public enum MenuOptions {
    GET_ALL_NEWS("1", "Get all news."),
    GET_NEWS_BY_ID("2", "Get news by id."),
    CREATE_NEWS("3", "Create news."),
    UPDATE_NEWS("4", "Update news."),
    REMOVE_NEWS_BY_ID("5", "Remove news by id."),
    GET_ALL_AUTHORS("6", "Get all authors."),
    GET_AUTHOR_BY_ID("7", "Get author by id."),
    CREATE_AUTHOR("8", "Create author."),
    UPDATE_AUTHOR("9", "Update author."),
    REMOVE_AUTHOR_BY_ID("10", "Remove author by id."),
    GET_ALL_TAGS("11", "Get all tags."),
    GET_TAG_BY_ID("12", "Get tag by id."),
    CREATE_TAG("13", "Create tag."),
    UPDATE_TAG("14", "Update tag."),
    REMOVE_TAG_BY_ID("15", "Remove tag by id."),
    GET_AUTHOR_BY_NEWS_ID("16", "Get author by news id."),
    GET_TAGS_BY_NEWS_ID("17", "Get tags by news id."),
    GET_NEWS_BY_TITLE("18", "Get news by title."),
    GET_NEWS_BY_CONTENT("19", "Get news by content."),
    GET_NEWS_BY_TAG_ID("20", "Get news by tag id."),
    GET_NEWS_BY_TAG_NAME("21", "Get news by tag name."),
    GET_NEWS_BY_AUTHOR_NAME("22", "Get news by author name."),
    EXIT("0", "Exit.");

    private String optionCode;
    private String optionName;

    MenuOptions() {
    }

    MenuOptions(String optionCode, String optionName) {
        this.optionCode = optionCode;
        this.optionName = optionName;
    }

    public String getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}