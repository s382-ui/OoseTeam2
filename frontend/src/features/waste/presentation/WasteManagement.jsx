import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { wasteService } from '../api/wasteApi';

const initialForm = {
  categoryCode: '', categoryName: '', categoryType: '지정',
  propertyInfo: '', disposalMethod: '', relatedLaw: '', isActive: 'Y',
};

const fields = [
  { name: 'categoryCode', label: '분류 코드', required: true },
  { name: 'categoryName', label: '분류명', required: true },
  { name: 'categoryType', label: '분류 유형', required: true, options: ['지정', '일반'] },
  { name: 'propertyInfo', label: '성상 정보', placeholder: '예: 액상·인화성' },
  { name: 'disposalMethod', label: '위탁 처리 방법' },
  { name: 'relatedLaw', label: '관련 법규' },
  { name: 'isActive', label: '사용 여부', required: true, options: [{ value: 'Y', label: '사용' }, { value: 'N', label: '미사용' }] },
];

export default function WasteManagement() {
  return (
    <EntityManagementPage
      title="폐기물 관리"
      registerTitle="폐기물(폐수) 분류 등록"
      inquiryTitle="폐기물(폐수) 분류 조회"
      initialForm={initialForm}
      idField="categoryCode"
      service={wasteService}
      fields={fields}
      searchFilters={[
        { name: 'categoryType', label: '분류 유형', options: ['지정', '일반'], exact: true },
        { name: 'categoryCode', label: '분류 코드' },
        { name: 'categoryName', label: '분류명' },
        { name: 'propertyInfo', label: '성상 정보' },
        { name: 'isActive', label: '사용 여부', options: [{ value: 'Y', label: '사용' }, { value: 'N', label: '미사용' }], exact: true },
      ]}
      columns={[
        { key: 'categoryCode', label: '분류 코드' },
        { key: 'categoryName', label: '분류명' },
        { key: 'categoryType', label: '유형' },
        { key: 'propertyInfo', label: '성상 정보' },
        { key: 'disposalMethod', label: '처리 방법' },
        { key: 'relatedLaw', label: '관련 법규' },
        { key: 'isActive', label: '사용 여부', render: (value) => value === 'Y' ? '사용' : '미사용' },
      ]}
    />
  );
}
