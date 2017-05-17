package com.touchsoft.timetracker.api.service;


import com.touchsoft.timetracker.api.dao.UserDao;
import com.touchsoft.timetracker.api.dao.UserException;
import com.touchsoft.timetracker.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Transactional(readOnly = true)
    public User getUser(String login) throws UserException {
        return userDao.getUserByLogin(login);
    }

    @Transactional(readOnly = true)
    public String getPasswordHash(CharSequence charSequence) {
        return userDao.getPasswordHash(charSequence);
    }

    @Transactional(readOnly = true)
    public List<User> getUsers(){
        return  userDao.getUsers();
    }
}

