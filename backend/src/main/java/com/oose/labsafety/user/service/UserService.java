package com.oose.labsafety.user.service;

import com.oose.labsafety.common.service.CrudService;
import com.oose.labsafety.user.domain.User;
import com.oose.labsafety.user.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}
