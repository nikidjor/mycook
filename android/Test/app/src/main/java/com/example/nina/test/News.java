package com.example.nina.test;

public class News {

    private String mDate;
    private String mTitle;
    private String mHouse;
    private String mUrl;

    public News(String date, String title,String house, String Url) {
       mDate = date;
       mTitle= title;
       mHouse = house;
       mUrl = Url;
    }


    public String getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }


    public String getHouse() {
        return mHouse;
   }

    public String getUrl(){return mUrl;}

}