package com.user.ledger.platform.jpaRepository;

import com.user.ledger.platform.jpaEntity.User;
import com.user.ledger.platform.jpaEntity.UserNote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface UserRepository extends CrudRepository<User, String> {

    public User getUserByUserId(String userId);

    public User save(User user);

    public User getUserByUserIdAndPassword(String userId, String password);

    @Query("SELECT t FROM UserNote t WHERE t.userId = ?1")
    public UserNote getUserNoteByUserId(String userId);

    public UserNote save(UserNote userNote);

}
