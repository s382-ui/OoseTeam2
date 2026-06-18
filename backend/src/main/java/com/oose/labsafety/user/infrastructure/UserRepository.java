package com.oose.labsafety.user.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oose.labsafety.common.infrastructure.JsonFileRepository;
import com.oose.labsafety.user.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends JsonFileRepository<User> {
    public UserRepository(ObjectMapper mapper, @Value("${labsafety.data-directory:data}") String directory) {
        super(mapper, User.class, "data/user/users.json", "user/users.json", directory);
    }
}
