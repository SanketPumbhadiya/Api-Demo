package com.example.api_demo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RetrofitModel {
    public int page;
    public int per_page;
    public int total;

    public int total_pages;
    @SerializedName("data")
    public ArrayList<Data> data;
    private Support support;

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }


    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public static class Data {
        public int id;
        public String email;
        public String first_name;
        public String last_name;
        public String avatar;

        public Data(int id, String email, String first_name, String last_name, String avatar) {
            this.id = id;
            this.email = email;
            this.first_name = first_name;
            this.last_name = last_name;
            this.avatar = avatar;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirstName(String firstName) {
            this.first_name = firstName;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLastName(String lastName) {
            this.last_name = lastName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setImage(String image) {
            this.avatar = image;
        }
    }

    public class Support {
        public String url;
        public String text;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

}