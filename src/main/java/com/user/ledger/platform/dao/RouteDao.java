package com.user.ledger.platform.dao;

import com.user.ledger.platform.responses.GenericResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteDao {

    public GenericResponse shortestRoute(String source, String destination);

}
