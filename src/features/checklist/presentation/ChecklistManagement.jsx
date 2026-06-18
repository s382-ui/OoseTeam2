import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { checklistService } from '../service/checklistService';

const initialForm = {
  itemId: '', itemName: '', categoryName: '', inspectionType: '일상점검',
  requiredYn: 'Y', useYn: 'Y', description: '',
};

export default function ChecklistManagement() {
  return (
    <EntityManagementPage
      title="일상점검 관리"
      registerTitle="체크리스트 항목 등록"
      inquiryTitle="체크리스트 항목 조회"
      initialForm={initialForm}
      idField="itemId"
      service={checklistService}
      searchFields={['itemId', 'itemName', 'categoryName', 'inspectionType']}
      fields={[
        { name: 'itemId', label: '항목 ID', required: true },
        { name: 'itemName', label: '점검 항목명', required: true },
        { name: 'categoryName', label: '분류명' },
        { name: 'inspectionType', label: '점검 유형', options: ['일상점검', '정기점검', '특별점검'] },
        { name: 'requiredYn', label: '필수 여부', options: ['Y', 'N'] },
        { name: 'useYn', label: '사용 여부', options: ['Y', 'N'] },
        { name: 'description', label: '설명' },
      ]}
      columns={[
        { key: 'itemId', label: '항목 ID' },
        { key: 'itemName', label: '점검 항목명' },
        { key: 'categoryName', label: '분류명' },
        { key: 'inspectionType', label: '점검 유형' },
        { key: 'requiredYn', label: '필수 여부' },
        { key: 'useYn', label: '사용 여부' },
        { key: 'description', label: '설명' },
      ]}
    />
  );
}
