package com.example.demo.domain;

public class AddFavDTO {

    private Integer clientId;

    private Integer bookId;

    public AddFavDTO() {
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
