package com.oose.labsafety.user.service;

import com.oose.labsafety.common.AbstractCatalogService;
import com.oose.labsafety.user.domain.UserAccount;
import com.oose.labsafety.user.infrastructure.UserRepository;

public final class UserService extends AbstractCatalogService<UserAccount> {

    public UserService(UserRepository repository) {
        super(repository);
    }
}