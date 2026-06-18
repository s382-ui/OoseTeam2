import { createEntity } from '../../../shared/domain/createEntity';

const schema = {
  completionStandardId: { label: '이수기준 ID', required: true },
  researcherCategoryId: { label: '연구활동종사자 분류 ID', required: true },
  requiredHours: { label: '필수 이수시간', required: true, type: 'number', min: 1 },
  effectiveFrom: { label: '기준 적용 시작일', required: true },
  effectiveTo: { label: '기준 적용 종료일', required: true },
};

export function createCompletionStandard(input) {
  const entity = createEntity(input, schema);
  if (entity.effectiveFrom > entity.effectiveTo) {
    throw new Error('기준 적용 시작일은 종료일보다 늦을 수 없습니다.');
  }
  return entity;
}
