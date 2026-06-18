import { createEntity } from '../../../shared/domain/createEntity';

const schema = {
  inspectionCategoryId: { label: '점검분야 ID', required: true },
  categoryName: { label: '점검분야명', required: true },
  description: { label: '설명' },
  useYn: { label: '사용 여부', required: true },
  registeredAt: { label: '등록일' },
};

export function createInspectionCategory(input) {
  return createEntity(
    { ...input, registeredAt: input.registeredAt || new Date().toISOString().slice(0, 10) },
    schema
  );
}
