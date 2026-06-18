import UserManagement from '../features/user/presentation/UserManagement';
import LaboratoryManagement from '../features/laboratory/presentation/LaboratoryManagement';
import ChemicalManagement from '../features/chemical/presentation/ChemicalManagement';
import WasteManagement from '../features/waste/presentation/WasteManagement';
import ChecklistManagement from '../features/checklist/presentation/ChecklistManagement';
import InspectionManagement from '../features/inspection/presentation/InspectionManagement';
import SafetyEducationManagement from '../features/education/presentation/SafetyEducationManagement';

export const menuConfig = [
  { id: 'user', label: '사용자 관리', subsystemId: 'DSS-001', Component: UserManagement },
  { id: 'laboratory', label: '연구실 관리', subsystemId: 'DSS-002', Component: LaboratoryManagement },
  { id: 'chemical', label: '화학물질 관리', subsystemId: 'DSS-003', Component: ChemicalManagement },
  { id: 'waste', label: '폐기물 관리', subsystemId: 'DSS-004', Component: WasteManagement },
  { id: 'checklist', label: '일상점검 관리', subsystemId: 'DSS-005', Component: ChecklistManagement },
  { id: 'inspection', label: '점검 관리', subsystemId: 'DSS-006', Component: InspectionManagement },
  { id: 'education', label: '안전교육 관리', subsystemId: 'DSS-007', Component: SafetyEducationManagement },
];
