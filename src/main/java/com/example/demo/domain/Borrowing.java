package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "borrowing")
public class Borrowing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "startDate")
    private Date startDate;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "returned")
    private int returned;

    @ManyToOne
    @JsonManagedReference
    private Client client;

    @ManyToOne
    @JsonManagedReference
    private Book book;

    public Borrowing() {

    }

    public Borrowing(int id, Date startDate, Date endDate, int returned, Client client, Book book) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.returned = returned;
        this.client = client;
        this.book = book;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public int isReturned() {
        return returned;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrowing borrowing = (Borrowing) o;
        return Objects.equals(client, borrowing.client) &&
                Objects.equals(book, borrowing.book);
    }

    @Override
    public int hashCode() {

        return Objects.hash(client, book);
    }

}
