package com.oose.labsafety.education.presentation;

import com.oose.labsafety.education.service.ResearcherStandardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/education/researcher-standards")
public class ResearcherStandardController {
    private final ResearcherStandardService service;

    public ResearcherStandardController(ResearcherStandardService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResearcherStandardService.ResearcherStandardView> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody ResearcherStandardService.ResearcherStandardRequest request) {
        service.register(request);
    }
}
