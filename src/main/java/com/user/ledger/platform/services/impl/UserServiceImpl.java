package com.user.ledger.platform.services.impl;

import com.user.ledger.platform.requests.UserNoteRequest;
import com.user.ledger.platform.dao.UserDao;
import com.user.ledger.platform.requests.UserRegistrationOrLoginRequest;
import com.user.ledger.platform.responses.GenericResponse;
import com.user.ledger.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public GenericResponse registerUser(UserRegistrationOrLoginRequest userRegistrationOrLoginRequest){
        return userDao.registerUser(userRegistrationOrLoginRequest);
    }

    @Override
    public GenericResponse loginUser(UserRegistrationOrLoginRequest userRegistrationOrLoginRequest){
        return userDao.loginUser(userRegistrationOrLoginRequest);
    }

    @Override
    public List<String> getUserNotes(String userId){
        return userDao.getUserNotes(userId);
    }

    @Override
    public GenericResponse addUserNote(String userId, UserNoteRequest userNoteRequest){
        return userDao.addUserNote(userId, userNoteRequest);
    }

}
