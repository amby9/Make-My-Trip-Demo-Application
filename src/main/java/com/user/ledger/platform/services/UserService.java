package com.user.ledger.platform.services;

import com.user.ledger.platform.requests.UserNoteRequest;
import com.user.ledger.platform.requests.UserRegistrationOrLoginRequest;
import com.user.ledger.platform.responses.GenericResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    public GenericResponse registerUser(UserRegistrationOrLoginRequest userRegistrationOrLoginRequest);

    public GenericResponse loginUser(UserRegistrationOrLoginRequest userRegistrationOrLoginRequest);

    public List<String> getUserNotes(String userId);

    public GenericResponse addUserNote(String userId, UserNoteRequest userNoteRequest);

}
