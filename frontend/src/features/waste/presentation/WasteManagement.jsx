import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { wasteService } from '../api/wasteApi';

const initialForm = {
  categoryId: '', categoryName: '', categoryType: '폐액',
  characteristics: '', disposalMethod: '', relatedLaw: '', useYn: 'Y',
};

export default function WasteManagement() {
  return (
    <EntityManagementPage
      title="폐기물 관리"
      registerTitle="폐기물 분류 등록"
      inquiryTitle="폐기물 분류 조회"
      initialForm={initialForm}
      idField="categoryId"
      service={wasteService}
      searchFields={['categoryId', 'categoryName', 'categoryType']}
      fields={[
        { name: 'categoryId', label: '분류 ID', required: true },
        { name: 'categoryName', label: '분류명', required: true },
        { name: 'categoryType', label: '폐기물 유형', options: ['폐액', '고형 폐기물', '폐유기용매', '폐산', '폐알칼리', '혼합폐기물'] },
        { name: 'characteristics', label: '특성' },
        { name: 'disposalMethod', label: '처리 방법' },
        { name: 'relatedLaw', label: '관련 법규' },
        { name: 'useYn', label: '사용 여부', options: ['Y', 'N'] },
      ]}
      columns={[
        { key: 'categoryId', label: '분류 ID' },
        { key: 'categoryName', label: '분류명' },
        { key: 'categoryType', label: '유형' },
        { key: 'characteristics', label: '특성' },
        { key: 'disposalMethod', label: '처리 방법' },
        { key: 'relatedLaw', label: '관련 법규' },
        { key: 'useYn', label: '사용 여부' },
      ]}
    />
  );
}
