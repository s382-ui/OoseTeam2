import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { inspectionService } from '../api/inspectionApi';

const initialForm = {
  categoryCode: '', categoryName: '', categoryDetail: '', useYn: 'true',
};

const booleanOptions = [{ value: 'true', label: '사용' }, { value: 'false', label: '미사용' }];
const booleanSearchOptions = [{ value: 'Y', label: '사용' }, { value: 'N', label: '미사용' }];
const fields = [
  { name: 'categoryCode', label: '분류 코드', required: true },
  { name: 'categoryName', label: '점검분야명', required: true },
  { name: 'categoryDetail', label: '상세 설명', fullWidth: true },
  { name: 'useYn', label: '사용 여부', required: true, options: booleanOptions },
];

export default function InspectionManagement() {
  return (
    <EntityManagementPage
      title="점검 관리(정기·정밀·수시)"
      registerTitle="점검분야 분류 등록"
      inquiryTitle="점검분야 분류 조회"
      initialForm={initialForm}
      idField="categoryCode"
      service={inspectionService}
      fields={fields}
      searchFilters={[
        { name: 'categoryCode', label: '분류 코드' },
        { name: 'categoryName', label: '점검분야명' },
        { name: 'categoryDetail', label: '상세 설명' },
        { name: 'useYn', label: '사용 여부', options: booleanSearchOptions, exact: true },
      ]}
      columns={[
        { key: 'categoryCode', label: '분류 코드' },
        { key: 'categoryName', label: '점검분야명' },
        { key: 'categoryDetail', label: '상세 설명' },
        { key: 'useYn', label: '사용 여부' },
        { key: 'createdAt', label: '등록일시' },
      ]}
    />
  );
}
