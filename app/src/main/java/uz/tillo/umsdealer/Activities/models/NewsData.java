package uz.tillo.umsdealer.Activities.models;

public class NewsData {
    String news_title;
    String news_text;
    String url;

    public String getUrl() {
        return url;
    }


    public String getNews_title() {
        return news_title;
    }

    public String getNews_text() {
        return news_text;
    }


    public NewsData(String news_text, String news_title, String url) {
        this.news_title = news_title;
        this.news_text = news_text;
        this.url = url;

    }


}
