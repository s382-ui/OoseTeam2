package com.oose.labsafety.bootstrap;

import com.oose.labsafety.chemical.service.ChemicalService;
import com.oose.labsafety.chemical.infrastructure.ChemicalRepository;
import com.oose.labsafety.chemical.presentation.ChemicalMenu;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.education.service.EducationCompletionControl;
import com.oose.labsafety.education.service.EducationReferenceControl;
import com.oose.labsafety.education.service.ResearcherStandardControl;
import com.oose.labsafety.education.infrastructure.EducationRepository;
import com.oose.labsafety.education.presentation.CompletionHistorySearchView;
import com.oose.labsafety.education.presentation.CompletionResultRegisterView;
import com.oose.labsafety.education.presentation.EducationMenu;
import com.oose.labsafety.education.presentation.ResearcherStandardRegisterView;
import com.oose.labsafety.education.presentation.ResearcherStandardSearchView;
import com.oose.labsafety.inspection.service.InspectionService;
import com.oose.labsafety.inspection.infrastructure.InspectionRepository;
import com.oose.labsafety.inspection.presentation.InspectionMenu;
import com.oose.labsafety.laboratory.service.LaboratoryService;
import com.oose.labsafety.laboratory.infrastructure.LaboratoryRepository;
import com.oose.labsafety.laboratory.presentation.LaboratoryMenu;
import com.oose.labsafety.ui.MainMenu;
import com.oose.labsafety.user.service.UserService;
import com.oose.labsafety.user.infrastructure.UserRepository;
import com.oose.labsafety.user.presentation.UserMenu;
import com.oose.labsafety.waste.service.WasteService;
import com.oose.labsafety.waste.infrastructure.WasteRepository;
import com.oose.labsafety.waste.presentation.WasteMenu;

import java.util.List;

public final class ApplicationBootstrap {

    public void run() {
        ApplicationContext context = createContext();
        new MainMenu(context).run();
    }

    public ApplicationContext createContext() {
        EducationRepository educationRepository = new EducationRepository();
        ResearcherStandardControl researcherStandardControl = new ResearcherStandardControl(educationRepository);
        EducationCompletionControl educationCompletionControl = new EducationCompletionControl(educationRepository);
        EducationReferenceControl educationReferenceControl = new EducationReferenceControl(educationRepository);

        return new ApplicationContext(
                new ConsoleIO(),
                List.of(
                        new UserMenu(new UserService(new UserRepository())),
                        new LaboratoryMenu(new LaboratoryService(new LaboratoryRepository())),
                        new ChemicalMenu(new ChemicalService(new ChemicalRepository())),
                        new WasteMenu(new WasteService(new WasteRepository())),
                        new InspectionMenu(new InspectionService(new InspectionRepository())),
                        new EducationMenu(
                                new ResearcherStandardRegisterView(researcherStandardControl),
                                new ResearcherStandardSearchView(researcherStandardControl),
                                new CompletionResultRegisterView(educationCompletionControl, educationReferenceControl),
                                new CompletionHistorySearchView(educationCompletionControl)
                        )
                )
        );
    }
}
