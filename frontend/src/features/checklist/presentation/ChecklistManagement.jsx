import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { checklistService } from '../api/checklistApi';

const initialForm = {
  itemId: '', itemName: '', categoryName: '', score: '',
  inspectionType: '일상점검', requiredYn: 'true', useYn: 'true',
};

const booleanOptions = [{ value: 'true', label: 'Y' }, { value: 'false', label: 'N' }];
const booleanSearchOptions = [{ value: 'Y', label: 'Y' }, { value: 'N', label: 'N' }];
const fields = [
  { name: 'itemId', label: '항목 ID', required: true },
  { name: 'itemName', label: '점검 항목명', required: true },
  { name: 'categoryName', label: '점검 분야명', required: true },
  { name: 'score', label: '배점', type: 'number', min: 0, required: true },
  { name: 'inspectionType', label: '점검 유형', required: true, options: ['일상점검', '정기점검', '정밀안전진단', '수시점검'] },
  { name: 'requiredYn', label: '필수 여부', required: true, options: booleanOptions },
  { name: 'useYn', label: '사용 여부', required: true, options: booleanOptions },
];

export default function ChecklistManagement() {
  return (
    <EntityManagementPage
      title="일상점검 관리"
      registerTitle="체크리스트 빌더 항목 등록"
      inquiryTitle="체크리스트 항목 조회"
      initialForm={initialForm}
      idField="itemId"
      service={checklistService}
      fields={fields}
      copyable
      searchFilters={[
        { name: 'itemName', label: '점검 항목명' },
        { name: 'categoryName', label: '분야명' },
        { name: 'inspectionType', label: '점검 유형', options: ['일상점검', '정기점검', '정밀안전진단', '수시점검'], exact: true },
        { name: 'useYn', label: '사용 여부', options: booleanSearchOptions, exact: true },
      ]}
      columns={[
        { key: 'itemId', label: '항목 ID' },
        { key: 'itemName', label: '점검 항목명' },
        { key: 'categoryName', label: '분야명' },
        { key: 'score', label: '배점' },
        { key: 'inspectionType', label: '점검 유형' },
        { key: 'requiredYn', label: '필수 여부' },
        { key: 'useYn', label: '사용 여부' },
        { key: 'createdAt', label: '등록일시' },
      ]}
    />
  );
}
