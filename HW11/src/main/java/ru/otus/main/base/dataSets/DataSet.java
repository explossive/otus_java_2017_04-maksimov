package ru.otus.main.base.dataSets;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class DataSet implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
