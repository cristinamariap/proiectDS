package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "favlist")
public class FavList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "clientId")
    private int clientId;

    @Column(name = "bookId")
    private int bookId;

    public FavList(){
        super();
    }

    public FavList(int clientId, int bookId) {
        this.clientId = clientId;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavList favList = (FavList) o;
        return id == favList.id &&
                clientId == favList.clientId &&
                bookId == favList.bookId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, clientId, bookId);
    }
}
