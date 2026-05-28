package com.oose.labsafety.bootstrap;

import com.oose.labsafety.chemical.application.ChemicalService;
import com.oose.labsafety.chemical.infrastructure.ChemicalRepository;
import com.oose.labsafety.chemical.presentation.ChemicalMenu;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.education.application.EducationService;
import com.oose.labsafety.education.infrastructure.EducationRepository;
import com.oose.labsafety.education.presentation.EducationMenu;
import com.oose.labsafety.inspection.application.InspectionService;
import com.oose.labsafety.inspection.infrastructure.InspectionRepository;
import com.oose.labsafety.inspection.presentation.InspectionMenu;
import com.oose.labsafety.laboratory.application.LaboratoryService;
import com.oose.labsafety.laboratory.infrastructure.LaboratoryRepository;
import com.oose.labsafety.laboratory.presentation.LaboratoryMenu;
import com.oose.labsafety.ui.MainMenu;
import com.oose.labsafety.user.application.UserService;
import com.oose.labsafety.user.infrastructure.UserRepository;
import com.oose.labsafety.user.presentation.UserMenu;
import com.oose.labsafety.waste.application.WasteService;
import com.oose.labsafety.waste.infrastructure.WasteRepository;
import com.oose.labsafety.waste.presentation.WasteMenu;

import java.util.List;

public final class ApplicationBootstrap {

    public void run() {
        ApplicationContext context = createContext();
        new MainMenu(context).run();
    }

    public ApplicationContext createContext() {
        return new ApplicationContext(
                new ConsoleIO(),
                List.of(
                        new UserMenu(new UserService(new UserRepository())),
                        new LaboratoryMenu(new LaboratoryService(new LaboratoryRepository())),
                        new ChemicalMenu(new ChemicalService(new ChemicalRepository())),
                        new WasteMenu(new WasteService(new WasteRepository())),
                        new InspectionMenu(new InspectionService(new InspectionRepository())),
                        new EducationMenu(new EducationService(new EducationRepository()))
                )
        );
    }
}