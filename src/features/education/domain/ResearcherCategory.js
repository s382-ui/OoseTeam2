import { createEntity } from '../../../shared/domain/createEntity';

const schema = {
  researcherCategoryId: { label: '연구활동종사자 분류 ID', required: true },
  categoryName: { label: '분류명', required: true },
  description: { label: '분류 설명' },
  active: { label: '사용 여부', type: 'boolean' },
};

export function createResearcherCategory(input) {
  return createEntity(input, schema);
}
