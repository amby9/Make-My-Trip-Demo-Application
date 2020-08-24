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
import com.user.ledger.platform.utils.Util;
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
        User user = userRepository.getUserByUserId(Util.enCode(userRegistrationOrLoginRequest.getUsername()));
        if (user != null) {
            log.error("User with this userId already exists!");
            throw new UserLedgerPlatformException(HttpStatus.SC_BAD_REQUEST, ErrorCodes.USER_ID_ALREADY_EXISTS_ERROR);
        }
        User user1 = userRepository.save(User.builder().userId(Util.enCode(userRegistrationOrLoginRequest.getUsername())).password(Util.enCode(userRegistrationOrLoginRequest.getPassword())).build());
        return GenericResponse.builder().status("account created").build();
    }

    @Override
    public GenericResponse loginUser(UserRegistrationOrLoginRequest userRegistrationOrLoginRequest){
        User user = userRepository.getUserByUserIdAndPassword(Util.enCode(userRegistrationOrLoginRequest.getUsername()), Util.enCode(userRegistrationOrLoginRequest.getPassword()));
        if(user!=null){
            return GenericResponse.builder().status("success").userId(userRegistrationOrLoginRequest.getUsername()).build();
        }
        else {
            log.error("Wrong login credentials provided");
            throw new UserLedgerPlatformException(HttpStatus.SC_UNAUTHORIZED, ErrorCodes.WRONG_CREDENTIAL_ERROR);
        }
    }

    @Override
    public List<String> getUserNotes(String userId){
        UserNote userNote = userRepository.getUserNoteByUserId(Util.enCode(userId));
        if(userNote!=null) {
            List<String> userNotes = new ArrayList<>();
            userNotes.add(Util.deCode(userNote.getNote()));
            return userNotes;
        } else return new ArrayList<>();
    }

    @Override
    public GenericResponse addUserNote(String userId, UserNoteRequest userNoteRequest) {
        User user = userRepository.getUserByUserId(Util.enCode(userId));
        if (user == null) {
            throw new UserLedgerPlatformException(HttpStatus.SC_UNAUTHORIZED, ErrorCodes.WRONG_CREDENTIAL_ERROR);
        }
        try {
            UserNote userNote = userRepository.save(UserNote.builder().userId(Util.enCode(userId)).note(Util.enCode(userNoteRequest.getNote())).build());
            return GenericResponse.builder().status("success").build();
        } catch (Exception e) {
            log.error("Exception occurred while saving note : ", e);
            throw new UserLedgerPlatformException(HttpStatus.SC_INTERNAL_SERVER_ERROR, ErrorCodes.INTERNAL_SERVER_ERROR);
        }
    }

}
