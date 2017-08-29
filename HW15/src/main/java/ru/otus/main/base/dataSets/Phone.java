package ru.otus.main.base.dataSets;

import javax.persistence.*;

@Entity
@Table(name = "phones")
public class Phone extends DataSet {

    @Column(name = "number")
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Phone() {
    }

    public Phone(String number) {
        this.number = number;
    }


    public String getNumber() {
        return number;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number='" + number + '\'' +
                '}';
    }
}
