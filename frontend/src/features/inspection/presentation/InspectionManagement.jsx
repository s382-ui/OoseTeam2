import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { inspectionService } from '../api/inspectionApi';

const initialForm = {
  inspectionCategoryId: '', categoryName: '', description: '', useYn: 'Y',
};

export default function InspectionManagement() {
  return (
    <EntityManagementPage
      title="점검 관리"
      registerTitle="점검분야 분류 등록"
      inquiryTitle="점검분야 분류 조회"
      initialForm={initialForm}
      idField="inspectionCategoryId"
      service={inspectionService}
      searchFields={['inspectionCategoryId', 'categoryName', 'description']}
      fields={[
        { name: 'inspectionCategoryId', label: '점검분야 ID', required: true },
        { name: 'categoryName', label: '점검분야명', required: true },
        { name: 'description', label: '설명' },
        { name: 'useYn', label: '사용 여부', options: ['Y', 'N'] },
      ]}
      columns={[
        { key: 'inspectionCategoryId', label: '점검분야 ID' },
        { key: 'categoryName', label: '점검분야명' },
        { key: 'description', label: '설명' },
        { key: 'useYn', label: '사용 여부' },
        { key: 'registeredAt', label: '등록일' },
      ]}
    />
  );
}
