package com.touchsoft.timetracker.api.service;


import com.touchsoft.timetracker.api.dao.UserException;
import com.touchsoft.timetracker.api.model.User;

import java.util.List;

public interface UserService {

    User getUser(String login) throws UserException;

    String getPasswordHash(CharSequence charSequence);

    List<User> getUsers();
}
