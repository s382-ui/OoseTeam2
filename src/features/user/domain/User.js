import { createEntity } from '../../../shared/domain/createEntity';

const schema = {
  userId: { label: '사용자 ID', required: true },
  userName: { label: '이름', required: true },
  department: { label: '소속 부서' },
  role: { label: '역할', required: true },
  contact: { label: '연락처' },
  email: { label: '이메일' },
  accountStatus: { label: '계정 상태', required: true },
  registeredAt: { label: '등록일' },
};

export function createUser(input) {
  return createEntity(
    { ...input, registeredAt: input.registeredAt || new Date().toISOString().slice(0, 10) },
    schema
  );
}
