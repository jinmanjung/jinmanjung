package com.drogsin.rsspotal.model;

import org.json.JSONArray;

public class RSSItem {

    String title = "";
    String link = "";
    String description = "";
    String imageUrl = "";
    int imageHeight = 0;
    String pubDate = "";
    String creator = "";
    String date = "";

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageHeight() {
        return imageHeight;
    }
    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
