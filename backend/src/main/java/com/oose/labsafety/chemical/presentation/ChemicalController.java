package com.oose.labsafety.chemical.presentation;

import com.oose.labsafety.chemical.domain.Chemical;
import com.oose.labsafety.chemical.service.ChemicalService;
import com.oose.labsafety.common.presentation.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chemicals")
public class ChemicalController extends CrudController<Chemical> {
    public ChemicalController(ChemicalService service) {
        super(service);
    }
}
