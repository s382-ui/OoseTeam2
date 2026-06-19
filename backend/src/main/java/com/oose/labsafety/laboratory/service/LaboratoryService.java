package com.oose.labsafety.laboratory.service;

import com.oose.labsafety.common.service.CrudService;
import com.oose.labsafety.laboratory.domain.Laboratory;
import com.oose.labsafety.laboratory.infrastructure.LaboratoryRepository;
import com.oose.labsafety.user.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LaboratoryService extends CrudService<Laboratory> {
    private final UserRepository userRepository;

    public LaboratoryService(LaboratoryRepository repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    @Override
    public Laboratory register(Laboratory laboratory) {
        if (userRepository.findById(laboratory.managerId()).isEmpty()) {
            throw new IllegalArgumentException("등록되지 않은 연구실 책임자 ID입니다: " + laboratory.managerId());
        }
        boolean duplicatedLocation = repository.findAll().stream()
                .anyMatch(item -> item.buildingName().equalsIgnoreCase(laboratory.buildingName())
                        && item.floor().equalsIgnoreCase(laboratory.floor())
                        && item.roomNo().equalsIgnoreCase(laboratory.roomNo())
                        && !"N".equalsIgnoreCase(item.isActive()));
        if (duplicatedLocation) {
            throw new IllegalArgumentException("같은 건물, 층, 호실에 등록된 연구실이 있습니다.");
        }
        return super.register(laboratory);
    }
}
