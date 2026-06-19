package com.oose.labsafety.education.presentation;

import com.oose.labsafety.education.domain.EducationCompletionResult;
import com.oose.labsafety.education.service.EducationCompletionService;
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
@RequestMapping("/api/education/completion-results")
public class EducationCompletionController {
    private final EducationCompletionService service;

    public EducationCompletionController(EducationCompletionService service) {
        this.service = service;
    }

    @GetMapping
    public List<EducationCompletionService.CompletionHistoryView> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EducationCompletionResult register(@Valid @RequestBody EducationCompletionResult result) {
        return service.register(result);
    }
}
