import { createEntity } from '../../../shared/domain/createEntity';

const schema = {
  completionResultId: { label: '교육이수결과 ID', required: true },
  researcherId: { label: '연구활동종사자 ID', required: true },
  openingId: { label: '교육개설정보 ID', required: true },
  learningResultId: { label: '학습결과 ID' },
  logId: { label: '안전교육일지 ID' },
  completionDate: { label: '이수일', required: true },
  recognizedHours: { label: '인정시간', required: true, type: 'number', min: 1 },
  completionStatus: { label: '이수 상태', required: true },
  manualRegistration: { label: '수동 등록 여부', type: 'boolean' },
};

export function createEducationCompletionResult(input) {
  return createEntity(input, schema);
}
