package ru.otus.main.dbService.dao;

import ru.otus.main.base.dataSets.DataSet;
import ru.otus.main.base.dataSets.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDataSetDAO {
    private Session session;

    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    public void save(User dataSet) {
        session.save(dataSet);
    }

    public User read( long id) {
        return session.load(User.class, id);
    }

    public User readByName(String name) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> from = criteria.from(User.class);
        criteria.where(builder.equal(from.get("name"), name));
        Query<User> query = session.createQuery(criteria);
        return query.uniqueResult();
}

    public List<User> readAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        return session.createQuery(criteria).list();
    }
}
