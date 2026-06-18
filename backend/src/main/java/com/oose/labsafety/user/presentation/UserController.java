package com.oose.labsafety.user.presentation;

import com.oose.labsafety.common.presentation.CrudController;
import com.oose.labsafety.user.domain.User;
import com.oose.labsafety.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends CrudController<User> {
    public UserController(UserService service) {
        super(service);
    }
}
