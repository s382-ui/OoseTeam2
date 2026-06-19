package com.oose.labsafety.user.presentation;

import com.oose.labsafety.common.presentation.CrudController;
import com.oose.labsafety.user.domain.User;
import com.oose.labsafety.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends CrudController<User> {
    private final UserService service;

    public UserController(UserService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<User> registerAll(@Valid @RequestBody List<@Valid User> users) {
        return service.registerAll(users);
    }
}
