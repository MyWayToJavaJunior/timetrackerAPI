package com.touchsoft.timetracker.api.dao;


import com.touchsoft.timetracker.api.model.User;
import com.touchsoft.timetracker.util.hibernate.CustomHibernateDaoSupport;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "userDao")
public class UserDao extends CustomHibernateDaoSupport {

    @Transactional(readOnly = true)
    public User getUserByLogin(String login) throws UserException {
        Query query = getSessionFactory().getCurrentSession().createQuery("from User where login=:login");
        query.setString("login", login);
        try {
            query.list().get(0);
        } catch (IndexOutOfBoundsException ex) {
            throw new UserException();
        }
        return (User) query.list().get(0);

    }

    @Transactional(readOnly = true)
    public String getPasswordHash(CharSequence charSequence) {
        Query query = getSessionFactory().getCurrentSession().createSQLQuery("SELECT OLD_PASSWORD(:password)");
        query.setString("password", charSequence.toString());
        return new String((byte[]) query.list().get(0));
    }

    @Transactional(readOnly = true)
    public List<User> getUsers(){
        Query query = getSessionFactory().getCurrentSession().createQuery("from User");
        return (List<User>) query.list();
    }

}
