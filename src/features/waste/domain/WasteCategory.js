import { createEntity } from '../../../shared/domain/createEntity';

const schema = {
  categoryId: { label: '폐기물 분류 ID', required: true },
  categoryName: { label: '분류명', required: true },
  categoryType: { label: '폐기물 유형', required: true },
  characteristics: { label: '특성' },
  disposalMethod: { label: '처리 방법' },
  relatedLaw: { label: '관련 법규' },
  useYn: { label: '사용 여부', required: true },
  registeredAt: { label: '등록일' },
};

export function createWasteCategory(input) {
  return createEntity(
    { ...input, registeredAt: input.registeredAt || new Date().toISOString().slice(0, 10) },
    schema
  );
}
