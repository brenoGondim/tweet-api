package com.challenge.livesponsor.tweetapi.repository;

import com.challenge.livesponsor.tweetapi.model.entity.User;
import com.surrealdb.driver.SyncSurrealDriver;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends GenericRepository<User> implements IUserRepository {

    public UserRepository(SyncSurrealDriver driver) {
        super(driver, "user");
    }

}
