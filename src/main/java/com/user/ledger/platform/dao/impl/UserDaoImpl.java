package com.user.ledger.platform.dao.impl;

import com.user.ledger.platform.enums.ErrorCodes;
import com.user.ledger.platform.exceptions.UserLedgerPlatformException;
import com.user.ledger.platform.jpaRepository.UserRepository;
import com.user.ledger.platform.requests.UserNoteRequest;
import com.user.ledger.platform.dao.UserDao;
import com.user.ledger.platform.jpaEntity.User;
import com.user.ledger.platform.jpaEntity.UserNote;
import com.user.ledger.platform.requests.UserRegistrationOrLoginRequest;
import com.user.ledger.platform.responses.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public GenericResponse registerUser(UserRegistrationOrLoginRequest userRegistrationOrLoginRequest) {
        User user = userRepository.getUserByUserId(userRegistrationOrLoginRequest.getUsername());
        if (user != null) {
            log.error("User with this userId already exists!");
            throw new UserLedgerPlatformException(HttpStatus.SC_BAD_REQUEST, ErrorCodes.INTERNAL_SERVER_ERROR);
        }
        User user1 = userRepository.save(User.builder().userId(userRegistrationOrLoginRequest.getUsername()).password(userRegistrationOrLoginRequest.getPassword()).build());
        return GenericResponse.builder().status("account created").build();
    }

    @Override
    public GenericResponse loginUser(UserRegistrationOrLoginRequest userRegistrationOrLoginRequest){
        User user = userRepository.getUserByUserIdAndPassword(userRegistrationOrLoginRequest.getUsername(), userRegistrationOrLoginRequest.getPassword());
        if(user!=null){
            return GenericResponse.builder().status("success").userId(userRegistrationOrLoginRequest.getUsername()).build();
        }
        else {
            log.error("Wrong login credentials provided");
            throw new UserLedgerPlatformException(HttpStatus.SC_UNAUTHORIZED, ErrorCodes.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<String> getUserNotes(String userId){
        UserNote userNote = userRepository.getUserNoteByUserId(userId);
        if(userNote!=null) {
            List<String> userNotes = new ArrayList<>();
            userNotes.add(userNote.getNote());
            return userNotes;
        } else return new ArrayList<>();
    }

    @Override
    public GenericResponse addUserNote(String userId, UserNoteRequest userNoteRequest){
        UserNote userNote = userRepository.save(UserNote.builder().userId(userId).note(userNoteRequest.getNote()).build());
        return GenericResponse.builder().status("success").build();
    }

}
