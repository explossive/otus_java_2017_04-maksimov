package ru.otus.main.base.entity;

import javax.persistence.*;

@Entity
@Table(name = "phones")
public class Phone extends DataSet {

    @Column(name = "code")
    private int code;

    @Column(name = "number")
    private String number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Phone() {
    }

    public Phone(int code, String number) {
        this.code = code;
        this.number = number;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "code=" + code +
                ", number='" + number + '\'' +
                '}';
    }
}