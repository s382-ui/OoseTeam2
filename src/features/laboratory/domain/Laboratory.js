import { createEntity } from '../../../shared/domain/createEntity';

const schema = {
  labId: { label: '연구실 ID', required: true },
  labName: { label: '연구실명', required: true },
  buildingName: { label: '건물명' },
  floor: { label: '층' },
  roomNo: { label: '호실' },
  departmentName: { label: '소속 부서' },
  managerId: { label: '관리자 ID' },
  managerName: { label: '관리자명' },
  contactNo: { label: '연락처' },
  managementGrade: { label: '관리 등급', required: true },
  isActive: { label: '사용 여부', required: true },
  createdAt: { label: '등록일' },
};

export function createLaboratory(input) {
  return createEntity(
    { ...input, createdAt: input.createdAt || new Date().toISOString().slice(0, 10) },
    schema
  );
}
