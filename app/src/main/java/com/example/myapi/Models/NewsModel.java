package com.example.myapi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModel
{
    @SerializedName ( "articles" )
    List<Items> articles;

    public NewsModel(List<Items> articles) {
        this.articles = articles;
    }

    public List<Items> getArticles() {
        return articles;
    }

    public class Items
    {
        @SerializedName ( "title" )
        String title;
        @SerializedName ( "description" )
        String desc;
        @SerializedName ( "urlToImage" )
        String image;
        @SerializedName ( "url" )
        String url;

        public Items(String title, String desc, String image, String url) {
            this.title = title;
            this.desc = desc;
            this.image = image;
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
