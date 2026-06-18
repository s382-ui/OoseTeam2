import { createEntity } from '../../../shared/domain/createEntity';

const schema = {
  openingId: { label: '교육개설정보 ID', required: true },
  courseId: { label: '교육과정 ID', required: true },
  startDate: { label: '개설 시작일', required: true },
  endDate: { label: '개설 종료일', required: true },
  status: { label: '개설 상태', required: true },
};

export function createEducationOpening(input) {
  const entity = createEntity(input, schema);
  if (entity.startDate > entity.endDate) {
    throw new Error('개설 시작일은 종료일보다 늦을 수 없습니다.');
  }
  return entity;
}
