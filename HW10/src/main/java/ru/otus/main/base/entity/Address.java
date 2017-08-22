package ru.otus.main.base.entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address extends DataSet {

    @Column(name = "street")
    private String street;

    @Column(name = "postal_index")
    private int index;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Address() {
    }

    public Address(String street, int index) {
        this.street = street;
        this.index = index;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "street='" + street + '\'' +
                ", index=" + index +
                '}';
    }
}
