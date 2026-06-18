import { createEntity } from '../../../shared/domain/createEntity';

const schema = {
  itemId: { label: '항목 ID', required: true },
  itemName: { label: '점검 항목명', required: true },
  categoryName: { label: '분류명' },
  inspectionType: { label: '점검 유형', required: true },
  requiredYn: { label: '필수 여부', required: true },
  useYn: { label: '사용 여부', required: true },
  description: { label: '설명' },
  registeredAt: { label: '등록일' },
};

export function createChecklistItem(input) {
  return createEntity(
    { ...input, registeredAt: input.registeredAt || new Date().toISOString().slice(0, 10) },
    schema
  );
}
