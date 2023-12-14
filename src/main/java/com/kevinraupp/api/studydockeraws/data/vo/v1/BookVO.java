package com.kevinraupp.api.studydockeraws.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "author", "launch_date", "price", "address", "title"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable {

    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private String author;
    private Date launch_date;
    private double price;
    private String title;

    public BookVO() {
    }

    public BookVO(Long key, String author, Date launch_date, double price, String title) {
        this.key = key;
        this.author = author;
        this.launch_date = launch_date;
        this.price = price;
        this.title = title;
    }

    public BookVO(String author, Date launch_date, double price, String title) {
        this.author = author;
        this.launch_date = launch_date;
        this.price = price;
        this.title = title;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookVO book)) return false;
        return Objects.equals(key, book.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}