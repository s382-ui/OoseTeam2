package com.oose.labsafety.user.service;

import com.oose.labsafety.common.service.CrudService;
import com.oose.labsafety.user.domain.User;
import com.oose.labsafety.user.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService extends CrudService<User> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    public List<User> registerAll(List<User> users) {
        if (users.isEmpty()) {
            throw new IllegalArgumentException("등록할 사용자 데이터가 없습니다.");
        }

        Set<String> requestIds = new HashSet<>();
        for (User user : users) {
            if (!requestIds.add(user.userId())) {
                throw new IllegalArgumentException("업로드 파일에 중복된 사용자 ID가 있습니다: " + user.userId());
            }
            if (repository.findById(user.userId()).isPresent()) {
                throw new IllegalArgumentException("이미 등록된 사용자입니다: " + user.userId());
            }
        }
        return repository.saveAll(users);
    }
}
