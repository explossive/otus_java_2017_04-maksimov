package ru.otus.main.base.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends DataSet {

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Address.class, mappedBy = "user", orphanRemoval = true)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Phone.class, mappedBy = "user", orphanRemoval = true)
    private List<Phone> phones;

    public User() {
    }

    public User(long id, String name, Address address, Phone... phones) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        address.setUser(this);
        this.setPhones(Arrays.asList(phones));
        Arrays.asList(phones).forEach((p) -> p.setUser(this));
    }

    public User(String name, Address address, Phone... phones) {
        this.setId(-1);
        this.setName(name);
        this.setAddress(address);
        address.setUser(this);
        this.setPhones(Arrays.asList(phones));
        Arrays.asList(phones).forEach((p) -> p.setUser(this));

    }

    public User(String name, Address address, List<Phone> phones) {
        this.setId(-1);
        this.setName(name);
        this.setAddress(address);
        address.setUser(this);
        this.setPhones(phones);
        phones.forEach((p) -> p.setUser(this));
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    private void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    private void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", address=" + address.toString() +
                ", phones=" + phones.toString() +
                '}';
    }
}
