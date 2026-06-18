import { createEntity } from '../../../shared/domain/createEntity';

const schema = {
  courseId: { label: '교육과정 ID', required: true },
  courseName: { label: '과정명', required: true },
  educationType: { label: '교육 유형', required: true },
  defaultHours: { label: '기본 교육시간', type: 'number', min: 1 },
};

export function createEducationCourse(input) {
  return createEntity(input, schema);
}
