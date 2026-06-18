import { createEntity } from '../../../shared/domain/createEntity';

const schema = {
  chemicalId: { label: '화학물질 ID', required: true },
  chemicalName: { label: '화학물질명', required: true },
  casNumber: { label: 'CAS 번호' },
  manufacturerName: { label: '제조사' },
  quantity: { label: '수량', type: 'number', min: 0 },
  unit: { label: '단위' },
  storageLocation: { label: '보관 위치' },
  msdsYn: { label: 'MSDS 여부', required: true },
  status: { label: '상태', required: true },
  registeredAt: { label: '등록일' },
};

export function createChemical(input) {
  return createEntity(
    { ...input, registeredAt: input.registeredAt || new Date().toISOString().slice(0, 10) },
    schema
  );
}
